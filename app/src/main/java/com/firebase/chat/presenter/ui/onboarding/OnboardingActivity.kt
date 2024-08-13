package com.firebase.chat.presenter.ui.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

/**
 * Created by Charles Raj I on 03/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
class OnboardingActivity : ComponentActivity() {

    val onboardingViewModel: OnboardingViewModel by viewModels<OnboardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OnboardingNavigation(onboardingViewModel)
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}