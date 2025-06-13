package com.example.dogfotosproject.data.local

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import androidx.core.content.edit

class UserSessionManager(context: Context) {

    companion object {
        private const val PREF_NAME = "user_session"
        private const val KEY_USER_LOGIN = "user_login"
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUserLogin(login: String) {
        sharedPrefs.edit { putString(KEY_USER_LOGIN, login) }
    }

    fun getUserLogin(): Flow<String?> = flow {
        emit(sharedPrefs.getString("user_login", null))
    }

    fun clearUserLogin() {
        sharedPrefs.edit { remove(KEY_USER_LOGIN) }
    }
}