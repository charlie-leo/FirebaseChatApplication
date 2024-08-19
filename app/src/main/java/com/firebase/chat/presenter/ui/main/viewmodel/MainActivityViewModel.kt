package com.firebase.chat.presenter.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.chat.data.model.UserModel
import com.firebase.chat.presenter.ui.main.event.MainScreenAction
import com.firebase.chat.presenter.ui.main.event.MainScreenEvent
import com.firebase.chat.ui.util.SnackBarState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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

    private val _mainScreenEvent = MutableStateFlow(MainScreenEvent(
        currentUser = UserModel(
            mobileNumber = currentUser?.phoneNumber,
            userId = currentUser?.uid
        )
    ))
    val mainScreenEvent: StateFlow<MainScreenEvent> = _mainScreenEvent.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    init {

        loadUserList()
    }


    fun action(event: MainScreenAction){
        _isLoading.value = true
        when(event){
            is MainScreenAction.SelectUser -> initChat(event)
        }
    }


    private fun loadUserList() {
        _isLoading.value = true
        viewModelScope.launch (Dispatchers.IO) {
            if (currentUser == null ){
                return@launch
            }
            databaseRef.getReference("users").addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<UserModel>()
                    Log.d("TAG", "onDataChange: ${snapshot.children.count()}")
                    for (userSnapshot in snapshot.children){
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

        try{

            viewModelScope.launch(Dispatchers.IO) {
                databaseRef.getReference("chats")
                    .addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (dataSnapshot.hasChildren()){
                                val chatQuery = dataSnapshot.ref.orderByChild("users/${_mainScreenEvent.value.currentUser?.userId}").equalTo(true)

                               chatQuery.get().addOnSuccessListener { branchSnap ->
                                    _isLoading.value = false
                                    var chatId: String? = null

                                    for (chatSnapshot in  branchSnap.children){
                                        val chatUsers = chatSnapshot.child("users").value as? Map<*, *>
                                        if (chatUsers?.containsKey(event.userModel.userId) == true){
                                            chatId = chatSnapshot.key
                                            _mainScreenEvent.value = _mainScreenEvent.value.copy(
                                                currentChatId = chatId
                                            )
                                            break
                                        }
                                    }

                                    if (chatId == null){
                                        chatId = dataSnapshot.ref.push().key

                                        val  chatData = mapOf(
                                            "${_mainScreenEvent.value.currentUser?.userId}" to true,
                                            "${event.userModel.userId}" to true,
                                        )
                                        dataSnapshot.ref.child(chatId ?: "").child("users").setValue(chatData)
                                        _mainScreenEvent.value = _mainScreenEvent.value.copy(
                                            currentChatId = chatId
                                        )
                                    }
                                }
                            } else {
                                _isLoading.value = false
                                val chatId = databaseRef.getReference("chats").push().key

                                val  chatData = mapOf(
                                    "${_mainScreenEvent.value.currentUser?.userId}" to true,
                                    "${event.userModel.userId}" to true,
                                )
                                dataSnapshot.ref.child(chatId ?: "").child("users").setValue(chatData)
                                _mainScreenEvent.value = _mainScreenEvent.value.copy(
                                    currentChatId = chatId
                                )
                            }
                        }

                        override fun onCancelled(p0: DatabaseError) {

                            _isLoading.value = false
                        }

                    })

//                }
            }
        }catch (e : Exception){
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