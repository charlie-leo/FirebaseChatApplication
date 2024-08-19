package com.firebase.chat.presenter.ui.main.event

import com.firebase.chat.data.model.UserModel

/**
 * Created by Charles Raj I on 17/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
sealed interface MainScreenAction {

    data class SelectUser(val userModel: UserModel) : MainScreenAction


}