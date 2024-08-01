package com.firebase.chat.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.firebase.chat.onboarding.screen.Navigation
import com.firebase.chat.onboarding.screen.VideoPlayerScreen
import com.firebase.chat.ui.theme.FirebaseChatApplicationTheme

/**
 * Created by Charles Raj I on 30/07/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
class OnboardingActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirebaseChatApplicationTheme {
//                Navigation()
                VideoPlayerScreen()
            }
        }
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}