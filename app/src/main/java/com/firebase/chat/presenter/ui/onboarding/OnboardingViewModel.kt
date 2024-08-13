package com.firebase.chat.presenter.ui.onboarding

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.chat.data.model.UserModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * Created by Charles Raj I on 13/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
class OnboardingViewModel : ViewModel(){

    val auth = FirebaseAuth.getInstance()
    val databaseRef = FirebaseDatabase.getInstance()

    lateinit var activity: Activity

    var storageVerificationId: String = ""

    fun provideActivity(activity: Activity) {
        this.activity = activity
    }

    var userModel = UserModel()

    fun action(even : OnboardingEvents){
        when (even) {
            is OnboardingEvents.LoginUpClick -> loginClick()
            is OnboardingEvents.OtpVerificationClick -> otpVerification("")
            is OnboardingEvents.SignUpClick -> signUpClick(even.userModel)
        }
    }

    private fun loginClick() {

    }

    private fun signUpClick(userModel: UserModel) {
        this.userModel = userModel
        viewModelScope.launch(Dispatchers.IO) {

            databaseRef.getReference("users")
                .orderByChild("mobileNumber")
                .equalTo(userModel.mobileNumber)
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(datasnap: DataSnapshot) {
                        if (datasnap.exists()){
                            Log.e("TAG", "User Already exist.")
                        } else {
                            sendOtp(userModel)
                        }
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Log.e("TAG", "onCancelled: " + p0.message )
                    }
                })
        }

    }

    private fun otpVerification(code: String) {
        val credentials = PhoneAuthProvider.getCredential(storageVerificationId,code)
        verifyOtp(credentials)
    }

    private fun sendOtp(userModel: UserModel) {

        try {
            viewModelScope.launch {
                val number = "+1" + userModel.mobileNumber
                val option = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(number)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(activity)
                    .setCallbacks(object  : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                        }

                        override fun onVerificationFailed(p0: FirebaseException) {

                        }

                        override fun onCodeSent(
                            verificationId: String,
                            token: PhoneAuthProvider.ForceResendingToken
                        ) {
//                            super.onCodeSent(p0, p1)
                            storageVerificationId = verificationId
                        }
                    })
                    .build()

                PhoneAuthProvider.verifyPhoneNumber(option)
            }
        } catch (e : Exception) {

        }




    }

    private fun verifyOtp(credentials: PhoneAuthCredential) {
        try {
            viewModelScope.launch {
                auth.signInWithCredential(credentials)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            viewModelScope.launch {

                                val key = databaseRef.getReference("users").push().key
                                key?.let {
                                    databaseRef.getReference("users").child(it).setValue(userModel)
                                        .addOnSuccessListener {

                                        }
                                        .addOnFailureListener{

                                        }
                                }


                            }

                        } else {

                        }
                    }

            }

        }catch (e : Exception){

        }
    }



    override fun onCleared() {
        super.onCleared()
    }

}