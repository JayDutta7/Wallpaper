package app.matrix.wallpaperpexels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import app.matrix.wallpaperpexels.database.rooMDb.WallPaperDB
import app.matrix.wallpaperpexels.database.sharedPref.LocalSharedPreference
import com.google.firebase.FirebaseApp

class WallPaperApp : Application() {

    /*SharedPreference*/
    private var localdatabase: LocalSharedPreference? = null
    /*RoomDatabase*/
    private var roomDB:WallPaperDB?=null

    companion object {
        private val TAG: String = WallPaperApp::class.java.simpleName

        private var instance: WallPaperApp? = null


        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }

        //calling  SharedPref through application class
        fun getPref(): LocalSharedPreference {
            Log.e(TAG, "LocalDatabase Initialized")
            return instance!!.localdatabase as LocalSharedPreference
        }

        //calling  RoomDatabase through application class
        fun getRoomDatabase():WallPaperDB{
            Log.e(TAG, "Room--LocalDatabase Initialized")
            return instance!!.roomDB as WallPaperDB
        }

    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        //initialize multidex for over 65k methods in application class
        MultiDex.install(this)

        Log.e(TAG, "This is Application Class Oncreate")

        //initialize localdatabase in the application class
        localdatabase = LocalSharedPreference(applicationContext)
        //initialize Firebase sdk in application class
        FirebaseApp.initializeApp(applicationContext)
        //initialize Roomdatabase in the application class
        roomDB=WallPaperDB.getDatabase(applicationContext)

    }//End Of Oncreate


}