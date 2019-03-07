package app.matrix.wallpaperpexels.localdatabase

import android.content.Context
import android.content.SharedPreferences

class LocalSharedPreference(private val context: Context) {

    private val PREFsNAME = Constant.application

    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFsNAME, Context.MODE_PRIVATE)

    //shared string value to shared pref
    fun save(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }
    //shared int value to shared pref
    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }
    //Saving boolean value to shared pref
    fun save(KEY_NAME: String, status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_NAME, status)
        editor.apply()
    }
    //Saving float value to sharedpref
    fun save(KEY_NAME: String,value: Float){
        val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.putFloat(KEY_NAME,value)
        editor.apply()
    }

    //Getting String Value
    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }
    //Getting Int Value
    fun getValueInt(KEY_NAME: String): Int {
        return sharedPref.getInt(KEY_NAME, 0)
    }
    //Getting Boolean Value
    fun getValueBoolien(KEY_NAME: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(KEY_NAME, defaultValue)
    }
    //Clear all shared preference
    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
    //Clear a particular value from shared preference
    fun removeValue(KEY_NAME: Any?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME as String?)
        editor.apply()
    }
}
