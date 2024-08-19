package com.firebase.chat.presenter.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.firebase.chat.presenter.ui.FirebaseApp
import com.firebase.chat.presenter.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Charles Raj I on 03/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
class OnboardingActivity : ComponentActivity() {

    val onboardingViewModel: OnboardingViewModel by viewModels<OnboardingViewModel>()

    var currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onboardingViewModel.provideActivity(this)
        setContent {
            if (currentUser != null) {
                this.startActivity(Intent(this, MainActivity::class.java).apply {

                })
            }

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