package com.firebase.chat.presenter.ui.main.event

import com.firebase.chat.data.model.UserModel

/**
 * Created by Charles Raj I on 17/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
data class MainScreenEvent(
    val currentUser: UserModel? = null,
    val selectedUser: UserModel? = null,
    val userList : MutableList<UserModel>? = null,
    val currentChatId : String? = null
)
