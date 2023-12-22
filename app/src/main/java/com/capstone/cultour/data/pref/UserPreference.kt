package com.capstone.cultour.data.pref

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.cultour.data.api.login.LoginResult

val Context.dataStore by preferencesDataStore("settings")
class UserPreference(context: Context) {

    companion object{
        private const val REFS_NAME = "user_preference"
        private const val USERID = "userId"
        private const val NAME = "name"
        private const val TOKEN = "token"


    }

    private val preferences = context.getSharedPreferences(REFS_NAME, Context.MODE_PRIVATE)

    fun putUser(loginResult: LoginResult){
        val editor = preferences.edit()
        editor.putString(TOKEN, loginResult.token)
        editor.putString(NAME, loginResult.name)
        loginResult.userId?.let { editor.putLong(USERID, it) }
        editor.apply()
    }

    fun gainUser(): LoginResult?{
        val gainUserId = preferences.getLong(USERID, 1)
        val gainToken = preferences.getString(TOKEN, "")
        val gainName = preferences.getString(NAME, "")
        return LoginResult(gainName, gainUserId, gainToken,)
    }


}