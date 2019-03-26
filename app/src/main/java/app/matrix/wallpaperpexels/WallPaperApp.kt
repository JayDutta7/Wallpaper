package app.matrix.wallpaperpexels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import app.matrix.wallpaperpexels.localdatabase.LocalSharedPreference
import com.google.firebase.FirebaseApp

class WallPaperApp : Application() {


    init {
        instance = this
    }

    companion object {
        private val TAG: String = WallPaperApp::class.java.simpleName

        private var instance: WallPaperApp? = null


        private var localdatabase: LocalSharedPreference? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }


        fun getPref(): LocalSharedPreference {
            if (localdatabase == null)
                localdatabase = LocalSharedPreference(applicationContext())
            return localdatabase as LocalSharedPreference
        }

    }

    override fun onCreate() {
        super.onCreate()
        //initialize multidex for over 65k methods in application class
        MultiDex.install(this)


        val context: Context = WallPaperApp.applicationContext()

        Log.e(TAG, "This is Application Class Oncreate")

        //initialize localdatabase in the application class
        localdatabase = LocalSharedPreference(this)
        //initialize Firebase sdk in application class
        FirebaseApp.initializeApp(applicationContext)

    }//End Of Oncreate


}