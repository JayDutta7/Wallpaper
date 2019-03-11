package app.matrix.wallpaperpexels

import android.app.Application
import android.util.Log
import androidx.multidex.MultiDex

class App : Application() {

    companion object {
        private val TAG:String= App::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Log.e(TAG,"This is Application Class Oncreate")


    }//End Of Oncreate






}