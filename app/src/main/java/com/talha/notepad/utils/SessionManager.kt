package com.talha.notepad.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class SessionManager(var ctx: Context) {
    private val pref: SharedPreferences
    private lateinit var editor: Editor
    fun putBoolValue(key: String?, value: Boolean) {
        editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolValue(KEY_NAME: String?): Boolean {
        return pref.getBoolean(KEY_NAME, false)
    }


    fun getStringValue(KEY_NAME: String?): String? {
        return pref.getString(KEY_NAME, "")
    }

    fun putString(key: String?, value: String?) {
        editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREFERENCE_NAME = "Notepad"
        const val USERNAME = "Name"
        const val EMAIL = "email"
        const val userPhoto = "userPhoto"
        const val isLogin = "isLogin"

    }

    init {
        pref = ctx.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE)
    }
}