package com.example.shopguns.services

import android.content.Context
import android.content.SharedPreferences

class MyPrefs(context: Context) {

    companion object {
        private const val PREFS_NAME = "my_prefs"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_AUTHORIZED = "authorized"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var userId: Long
        get() = prefs.getLong(KEY_USER_ID, -1)
        set(value) = prefs.edit().putLong(KEY_USER_ID, value).apply()

    var isAuthorised: Boolean
        get() = prefs.getBoolean(KEY_AUTHORIZED, false)
        set(value) = prefs.edit().putBoolean(KEY_AUTHORIZED, value).apply()

    fun clear() {
        prefs.edit().clear().apply()
    }
}