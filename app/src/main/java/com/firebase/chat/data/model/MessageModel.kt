package com.firebase.chat.data.model

import android.widget.EditText

/**
 * Created by Charles Raj I on 17/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */

data class MessageModel(
    val messageId : String? = null,
    val senderId : String? = null,
    val text: String? = null,
    val timeStamp: Long? = null
)
