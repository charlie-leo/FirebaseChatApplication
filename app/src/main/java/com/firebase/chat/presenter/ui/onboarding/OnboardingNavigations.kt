package com.firebase.chat.presenter.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.chat.presenter.ui.onboarding.screens.LoginScreen
import com.firebase.chat.presenter.ui.onboarding.screens.OnboardingScreen
import com.firebase.chat.presenter.ui.onboarding.screens.OtpScreen
import com.firebase.chat.presenter.ui.onboarding.screens.SignUpScreen

/**
 * Created by Charles Raj I on 13/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */

object OnboardingNavigationObject {
    const val ONBOARDING_SCREEN = "ONBOARDING_SCREEN"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
    const val SIGNUP_SCREEN = "SIGNUP_SCREEN"
    const val OTP_SCREEN = "OTP_SCREEN"

}



@Composable
fun OnboardingNavigation(onboardingViewModel: OnboardingViewModel) {

    val navController = rememberNavController()
//    val view Loca\


    NavHost(navController = navController,
        startDestination = OnboardingNavigationObject.ONBOARDING_SCREEN)
    {
        composable(
            route = OnboardingNavigationObject.ONBOARDING_SCREEN
        ) {
            OnboardingScreen(
                navController
            )
        }
     composable(
         route = OnboardingNavigationObject.LOGIN_SCREEN
     ) {
         LoginScreen(
             navController,
             onboardingViewModel::action
         )
     }

        composable(
            route = OnboardingNavigationObject.SIGNUP_SCREEN
        ) {
            SignUpScreen(
                navController,
                onboardingViewModel::action
            )
        }
        composable(
            route = OnboardingNavigationObject.OTP_SCREEN
        ) {
            OtpScreen(navController)
        }
    }

}