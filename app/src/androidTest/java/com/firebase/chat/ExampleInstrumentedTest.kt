package com.firebase.chat

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.firebase.chat.onboarding.screen.LoginScreen
import com.firebase.chat.onboarding.screen.Navigation

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.firebase.chat", appContext.packageName)
    }

    @Test
    fun sendOtp() {
        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithTag("mobileNumberTextFieldTag").assertExists()
        composeTestRule.onNodeWithTag("sendOtpBtnTag").assertExists()

        composeTestRule.onNodeWithTag("mobileNumberTextFieldTag")
            .performTextInput("8574635243")

        composeTestRule.onNodeWithTag("sendOtpBtnTag")
            .performClick()

        composeTestRule.onNodeWithTag("otpTextFieldTag").assertExists()
        composeTestRule.onNodeWithTag("otpButtonTag").assertExists()

        composeTestRule.onNodeWithTag("otpTextFieldTag")
            .performTextInput("234567")
        composeTestRule.onNodeWithTag("otpButtonTag")
            .performClick()

        composeTestRule.onNodeWithTag("mobileNumberTextFieldTag").assertExists()
        composeTestRule.onNodeWithTag("sendOtpBtnTag").assertExists()
    }


}