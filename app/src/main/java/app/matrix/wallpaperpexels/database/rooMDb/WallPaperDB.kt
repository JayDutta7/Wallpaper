package app.matrix.wallpaperpexels.database.rooMDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.matrix.ticketingsystem.database.localDatabase.dao.SampleDao
import app.matrix.wallpaperpexels.database.rooMDb.model.Example

@Database(entities = [Example::class], version = 1, exportSchema = false)
abstract class WallPaperDB : RoomDatabase() {

    /*Abstract class for getting the interface's*/
    abstract fun sampleDao(): SampleDao


    companion object {
        private var INSTANCE: WallPaperDB? = null

        fun getDatabase(context: Context): WallPaperDB {

            if (INSTANCE == null) {

                synchronized(WallPaperDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WallPaperDB::class.java,
                        "Wallpaper.db"
                    )/*.addCallback(object :Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                        }
                    })*/
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }

    }

}