package com.firebase.chat.presenter.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.chat.presenter.ui.onboarding.screens.LoginScreen
import com.firebase.chat.presenter.ui.onboarding.screens.OnboardingScreen
import com.firebase.chat.presenter.ui.onboarding.screens.OtpScreen
import com.firebase.chat.presenter.ui.onboarding.screens.SignUpScreen
import kotlinx.coroutines.launch

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

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val snackBarState by onboardingViewModel.snackBarState.collectAsState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { paddingValues ->
        val it = paddingValues
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
                OtpScreen(navController,onboardingViewModel::action)
            }
        }

       LaunchedEffect(key1 = snackBarState.show) {
           scope.launch {
               if (snackBarState.show){
                   snackBarHostState.showSnackbar(
                       message = snackBarState.message,
                       if(snackBarState.isError) "Error" else "Success",
                       duration = SnackbarDuration.Short
                   )
               }

           }
       }

        if (onboardingViewModel.isLoading.collectAsState().value){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    strokeCap = StrokeCap.Round,
                    strokeWidth = 8.dp,
                    trackColor = Color.White
                )
            }
        }

    }
}