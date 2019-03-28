package app.matrix.wallpaperpexels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import app.matrix.wallpaperpexels.localdatabase.LocalSharedPreference
import com.google.firebase.FirebaseApp

class WallPaperApp : Application() {


    private var localdatabase: LocalSharedPreference? = null

    companion object {
        private val TAG: String = WallPaperApp::class.java.simpleName

        private var instance: WallPaperApp? = null


        fun applicationContext(): Context {
            return instance!!.applicationContext
        }


        fun getPref(): LocalSharedPreference {
            Log.e(TAG, "LocalDatabase Initialized")
            return instance!!.localdatabase as LocalSharedPreference
        }

    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        //initialize multidex for over 65k methods in application class
        MultiDex.install(this)

        val context: Context = WallPaperApp.applicationContext()

        Log.e(TAG, "This is Application Class Oncreate")

        //initialize localdatabase in the application class
        localdatabase = LocalSharedPreference(context)
        //initialize Firebase sdk in application class
        FirebaseApp.initializeApp(applicationContext)

    }//End Of Oncreate


}