package com.firebase.chat.onboarding.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

/**
 * Created by Charles Raj I on 30/07/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */

object Navigation {
    const val LOGIN_SCREEN = "LoginScreen"
    const val OTP_SCREEN = "SenOtpScreen"
}

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.LOGIN_SCREEN
    ) {

        composable(route =  Navigation.LOGIN_SCREEN) {
            LoginScreen(navController)
        }

        composable(route = Navigation.OTP_SCREEN) {
            OtpScreen(navController)
        }



    }

}

//@Serializable
//data class LoginDataScreen(val id: Int)
//
//@Serializable
//data class OtpDataScreen(val id: Int)
////data class LoginScreen(val id : Int)