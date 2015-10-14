package tokyo.tommy_kw.kotlinsample.application

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.firebase.client.Firebase

/**
 * Created by tommy on 15/10/07.
 */
class App : Application() {
    override fun onTerminate() {
        super.onTerminate()
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
        Firebase.setAndroidContext(this);
    }
}