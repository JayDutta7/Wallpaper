package app.matrix.wallpaperpexels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import app.matrix.wallpaperpexels.localdatabase.LocalSharedPreference

class WallPaperApp : Application() {


    companion object {
        private val TAG: String = WallPaperApp::class.java.simpleName

        @SuppressLint("StaticFieldLeak")
        private var mcontext: Context? = null

        @SuppressLint("StaticFieldLeak")
        private var localdatabase: LocalSharedPreference? = null


        fun getPref(): LocalSharedPreference {
            if (localdatabase == null)
                localdatabase = LocalSharedPreference(mcontext!!)
            return localdatabase as LocalSharedPreference
        }

    }

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        mcontext = this
        Log.e(TAG, "This is Application Class Oncreate")


        localdatabase = LocalSharedPreference(this)


    }//End Of Oncreate


}