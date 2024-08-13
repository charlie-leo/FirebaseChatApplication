package com.firebase.chat.presenter.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Charles Raj I on 13/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */


@HiltAndroidApp
class FirebaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}