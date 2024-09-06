package com.firebase.chat.presenter.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.chat.data.model.MessageModel
import com.firebase.chat.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

/**
 * Created by Charles Raj I on 05/09/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
class ChatViewModel : ViewModel() {

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val databaseRef = FirebaseDatabase.getInstance().reference
    private var chatId: String? = null

    fun action(){

    }

    fun setChatId(chatId : String){
        this.chatId = chatId
    }

    fun getMessages(callBack: (list: List<MessageModel>) -> Unit) {
        chatId?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {

                databaseRef.child("chats").child(id).child("messages")
                    .orderByChild("timestamp")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val messages = dataSnapshot.children.mapNotNull { messageSnap ->
                                messageSnap.getValue(MessageModel::class.java)
                            }
                            callBack(messages.reversed())
                        }

                        override fun onCancelled(databaseError: DatabaseError) {

                        }

                    })

            }
        }


    }


    fun sendMessage(message: String, callBack: (status: Boolean) -> Unit) {
        var messageInstance = MessageModel(
            text = message,
            senderId = currentUser?.uid,
            timeStamp = System.currentTimeMillis()
        )

        chatId?.let { id ->
            viewModelScope.launch {

                withContext(Dispatchers.IO){
                    val chatNode = databaseRef.child("chats")
                    val messageId = chatNode.child(id).child("messages").push().key

                    try {
                        chatNode.child(id).child("messages")
                            .child(messageId?:"")
                            .setValue(messageInstance)
                            .addOnSuccessListener {
                                Log.d("TAG", "sendMessage: Success $message")
                                callBack(true)
                            }
                            .addOnFailureListener{ exception ->
                                callBack(false)
                                Log.e("TAG", "sendMessage: ", exception)
                            }

                    } catch (ex : Exception){
                        Log.e("TAG", "sendMessage: ", ex)
                    }

                }
            }
        }

    }



    override fun onCleared() {
        super.onCleared()

    }

}