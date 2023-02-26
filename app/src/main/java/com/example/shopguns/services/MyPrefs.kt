package com.example.shopguns.services

import android.content.Context
import android.content.SharedPreferences

class MyPrefs(context: Context) {
//зберігання налуштуваннь конкретної сесії користувача
    companion object {
        private const val PREFS_NAME = "my_prefs"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_AUTHORIZED = "authorized"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var userId: Long //ID авторизованого
        get() = prefs.getLong(KEY_USER_ID, -1)
        set(value) = prefs.edit().putLong(KEY_USER_ID, value).apply()

    var isAuthorised: Boolean //підтвердження авторизації і перевірка
        get() = prefs.getBoolean(KEY_AUTHORIZED, false)
        set(value) = prefs.edit().putBoolean(KEY_AUTHORIZED, value).apply()

    fun clear() { //очищення даних
        prefs.edit().clear().apply()
    }
}