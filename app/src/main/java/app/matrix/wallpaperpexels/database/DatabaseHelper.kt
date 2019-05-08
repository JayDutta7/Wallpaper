package app.matrix.wallpaperpexels.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import app.matrix.wallpaperpexels.database.rooMDb.model.UserDetailsData
import java.util.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        //Database version
        private val DATABASE_VERSION = 1
        //Database Name
        private val DATABASE_NAME = "UserDetails"
        //user table name
        private val TABLE_USER = "User"

        //user table column names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"
        private val COLUMN_USER_PHONENUMBER = "user_phonenumber"
    }

    private val CREATE_TABLE =
        (" CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_EMAIL + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_PHONENUMBER + " TEXT " + ")")

    /* private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
             + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
             + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")*/


    private val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        //Create Table Again
        onCreate(db)
    }

    fun getUserId(): Int {

        val userId = arrayOf(COLUMN_USER_ID)
        val databasE = this.readableDatabase

        val query=" SELECT * FROM $TABLE_USER WHERE $COLUMN_USER_ID = "

        val curSor = databasE.query(
            TABLE_USER,//Table to query
            userId,//columns to return
            null,//columns for where clause
            null,//columns for where clause
            null,//group the rows
            null,//filter by row groups
            null//the sort order
        )


        return 0
    }

    //Retrun all User from DB
    fun getAllUser(): List<UserDetailsData> {

        val UserList = ArrayList<UserDetailsData>()
        //array of columns to fetch
        val Columns =
            arrayOf(COLUMN_USER_ID, COLUMN_USER_EMAIL, COLUMN_USER_NAME, COLUMN_USER_PASSWORD, COLUMN_USER_PHONENUMBER)
        //sorting orders
        val sortOrder = " $COLUMN_USER_NAME ASC "

        val db = this.readableDatabase

        val cursor = db.query(
            TABLE_USER,//Table to query
            Columns,//columns to return
            null,//columns for where clause
            null,//columns for where clause
            null,//group the rows
            null,//filter by row groups
            sortOrder//the sort order
        )
        if (cursor.moveToFirst()) {
            do {
                val user = UserDetailsData(
                    id = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                    email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)),
                    password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)),
                    phonenumber = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONENUMBER))
                )
                //Add data to ArrayList
                UserList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return UserList
    }

    //Insert User
    fun addUser(userDetails: UserDetailsData) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_USER_NAME, userDetails.name)
        values.put(COLUMN_USER_EMAIL, userDetails.email)
        values.put(COLUMN_USER_PASSWORD, userDetails.password)
        values.put(COLUMN_USER_PHONENUMBER, userDetails.phonenumber)

        //Inserting row data
        db.insert(TABLE_USER, null, values)
        db.close()
    }

    //Update Particular User
    fun updateUser(userDetails: UserDetailsData) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_USER_NAME, userDetails.name)
        values.put(COLUMN_USER_EMAIL, userDetails.email)
        values.put(COLUMN_USER_PASSWORD, userDetails.password)
        values.put(COLUMN_USER_PHONENUMBER, userDetails.phonenumber)

        //Updating the row
        db.update(TABLE_USER, values, "$COLUMN_USER_PASSWORD=?", arrayOf(userDetails.password))
        db.close()
    }

    //Delete Particular User
    fun deleteUser(userDetails: UserDetailsData) {
        val db = this.writableDatabase

        db.delete(TABLE_USER, " $COLUMN_USER_EMAIL=? ", arrayOf(userDetails.email))
        db.close()
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //checking if DB have the particular email
    fun checkUserEmail(email: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = " $COLUMN_USER_EMAIL = ? "

        // selection argument
        val selectionArgs = arrayOf(email)

        val curSor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )


        if (curSor.count > 0)
            return true

        curSor.close()
        db.close()




        return false
    }


    //checking if DB have the particular email
    fun checkUserId(email: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = " $COLUMN_USER_EMAIL = ? "

        // selection argument
        val selectionArgs = arrayOf(email)

        val curSor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )


        if (curSor.count > 0)
            return true

        curSor.close()
        db.close()




        return false
    }

    //Checking user exists or not
    fun checkUserExits(email: String, password: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)

        val db = this.readableDatabase

        // selection criteria
        val selection = " $COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ? "

        // selection arguments
        val selectionArgs = arrayOf(email, password)

        // query user table with conditions
        val cursor = db.query(
            TABLE_USER, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null
        ) //The sort order

        val cursorCount = cursor.count

        if (cursorCount > 0)
            return true

        cursor.close()
        db.close()



        return false
    }


}