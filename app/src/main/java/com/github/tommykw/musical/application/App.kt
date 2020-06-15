package com.github.tommykw.musical.application

import android.app.Application
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import com.firebase.client.Firebase
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by tommykw on 15/10/07.
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this);
        Firebase.setAndroidContext(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}