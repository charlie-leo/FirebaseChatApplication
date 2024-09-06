package com.firebase.chat.presenter.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.chat.data.model.UserModel
import com.firebase.chat.presenter.ui.main.event.MainScreenAction
import com.firebase.chat.presenter.ui.main.event.MainScreenEvent
import com.firebase.chat.ui.util.SnackBarState
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Charles Raj I on 17/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
class MainActivityViewModel : ViewModel() {

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val databaseRef = FirebaseDatabase.getInstance()

    private val _snackBarState = MutableStateFlow(SnackBarState())
    val snackBarState: StateFlow<SnackBarState> = _snackBarState.asStateFlow()

    val chatViewModel: ChatViewModel = ChatViewModel()


    private val _mainScreenEvent = MutableStateFlow(
        MainScreenEvent(
            currentUser = UserModel(
                mobileNumber = currentUser?.phoneNumber,
                userId = currentUser?.uid
            )
        )
    )
    val mainScreenEvent: StateFlow<MainScreenEvent> = _mainScreenEvent.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

//    var chatJob = CompletableJob()

    init {

        loadUserList()
    }


    fun action(event: MainScreenAction) {
        _isLoading.value = true
        when (event) {
            is MainScreenAction.SelectUser -> initChat(event)
            is MainScreenAction.SendMessage -> chatViewModel.sendMessage(event.message){
                _isLoading.value = false
                event.callBack(it)
            }
        }
    }


    private fun loadUserList() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (currentUser == null) {
                return@launch
            }
            databaseRef.getReference("users")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = mutableListOf<UserModel>()
                        Log.d("TAG", "onDataChange: ${snapshot.children.count()}")
                        for (userSnapshot in snapshot.children) {
                            val userModel = userSnapshot.getValue(UserModel::class.java)
                            userModel?.let {
                                if (it.userId != currentUser.uid) {
                                    list.add(it)
                                }
                            }
                        }
                        Log.d("TAG", "onDataChange: ${list.toMutableList().size}")
                        _mainScreenEvent.value = _mainScreenEvent.value.copy(
                            userList = list.toMutableList()
                        )
                        _isLoading.value = false
                    }

                    override fun onCancelled(p0: DatabaseError) {
                        _isLoading.value = false
                        _snackBarState.value = _snackBarState.value.copy(
                            show = true,
                            isError = true,
                            message = p0.message
                        )
                    }
                })
        }
    }


    private fun initChat(event: MainScreenAction.SelectUser) {
        _mainScreenEvent.value = _mainScreenEvent.value.copy(
            selectedUser = event.userModel
        )

        try {
            viewModelScope.launch(Dispatchers.IO) {

                var dataSnapshot = databaseRef.getReference("chats").get().await()

                if (dataSnapshot.hasChildren()) {
                    try {
                        _isLoading.value = false
                        var chatId: String? = null

                        for (chatSnapshot in dataSnapshot.children) {
                            val chatUsers = chatSnapshot.child("users").value as? Map<*, *>
                            if (chatUsers?.containsKey(event.userModel.userId) == true
                                && chatUsers.containsKey(_mainScreenEvent.value.currentUser?.userId)) {
                                chatId = chatSnapshot.key
                                _mainScreenEvent.value = _mainScreenEvent.value.copy(
                                    currentChatId = chatId
                                )
                                chatViewModel.setChatId(chatId ?: "")
                                chatViewModel.getMessages {
                                    _mainScreenEvent.value = _mainScreenEvent.value.copy(
                                        messagesList = it
                                    )
                                }
                                break
                            }
                        }

                        if (chatId == null) {
                            chatId = dataSnapshot.ref.push().key
                            val chatData = mapOf(
                                "${_mainScreenEvent.value.currentUser?.userId}" to true,
                                "${event.userModel.userId}" to true,
                            )
                            dataSnapshot.ref.child(chatId ?: "").child("users").setValue(chatData)
                            _mainScreenEvent.value = _mainScreenEvent.value.copy(
                                currentChatId = chatId
                            )
                            chatViewModel.setChatId(chatId ?: "")
                            chatViewModel.getMessages {
                                _mainScreenEvent.value = _mainScreenEvent.value.copy(
                                    messagesList = it
                                )
                            }
                        }
                    } catch (ex: Exception) {
                        Log.e("TAG", "onDataChange: ", ex)
                    }

                } else {
                    _isLoading.value = false
                    val chatId = databaseRef.getReference("chats").push().key

                    val chatData = mapOf(
                        "${_mainScreenEvent.value.currentUser?.userId}" to true,
                        "${event.userModel.userId}" to true,
                    )
                    dataSnapshot.ref.child(chatId ?: "").child("users").setValue(chatData)
                    _mainScreenEvent.value = _mainScreenEvent.value.copy(
                        currentChatId = chatId
                    )
                }

//                databaseRef.getReference("chats")
//                    .addListenerForSingleValueEvent(object : ValueEventListener{
//                        override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                        }
//
//                        override fun onCancelled(p0: DatabaseError) {
//                            _isLoading.value = false
//                        }
//                    })
//                }
            }
        } catch (e: Exception) {
            _isLoading.value = false
            _snackBarState.value = _snackBarState.value.copy(
                show = true,
                isError = true,
                message = e.message ?: ""
            )
        }
    }




    override fun onCleared() {
        super.onCleared()
    }
}