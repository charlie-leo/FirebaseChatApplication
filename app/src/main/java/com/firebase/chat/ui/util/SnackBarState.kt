package com.firebase.chat.ui.util

/**
 * Created by Charles Raj I on 17/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
data class SnackBarState(
    val show : Boolean = false,
    val message : String = "",
    val isError : Boolean = false
)
