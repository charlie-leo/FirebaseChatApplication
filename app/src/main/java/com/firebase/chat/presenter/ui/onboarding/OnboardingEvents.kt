package com.firebase.chat.presenter.ui.onboarding

import com.firebase.chat.data.model.UserModel

/**
 * Created by Charles Raj I on 13/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
sealed interface OnboardingEvents {


    data class SignUpClick(val userModel: UserModel) : OnboardingEvents
    data class LoginUpClick(val userModel: UserModel) : OnboardingEvents
    data class OtpVerificationClick(val userModel: UserModel) : OnboardingEvents
}