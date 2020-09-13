package com.projectx.codeexercise

import android.content.Context
import android.content.SharedPreferences


const val KEY_SEARCH_HIS = "KEY_SEARCH_HIS"
const val KEY_RECENT_SEARCH = "KEY_RECENT_SEARCH"


class SharedPreferencesObject(context: Context) {

    private val settings: SharedPreferences = context.getSharedPreferences("PREF", 0)
    private val editor: SharedPreferences.Editor

    init {
        editor = settings.edit()
    }

    fun getString(key: String?, default_value: String?): String? {
        return settings.getString(key, default_value)
    }

    fun setString(key: String, value: String?) {
        editor.putString(key, value)
        editor.commit()
    }

    fun getStringSet(key: String?, default_value: MutableSet<String>? = null): MutableSet<String>? {
        return settings.getStringSet(key, default_value)
    }

    fun setStringSet(key: String, value: MutableSet<String>? = null) {
        editor.putStringSet(key, value)
        editor.commit()
    }

    fun getInt(key: String, default_value: Int = 0): Int {
        return settings.getInt(key, default_value)
    }

    fun setInt(key: String, value: Int = 0) {
        editor.putInt(key, value)
        editor.commit()
    }

    fun getBoolean(key: String, default_value: Boolean =  false): Boolean {
        return settings.getBoolean(key, default_value)
    }

    fun setBoolean(key: String?, value: Boolean = false) {
        editor.putBoolean(key, value)
        editor.commit()
    }


}