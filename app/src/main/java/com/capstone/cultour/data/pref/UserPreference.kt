package com.capstone.cultour.data.pref

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("settings")
class UserPreference(context: Context) {

    companion object{
        private const val REFS_NAME = "user_preference"
        private const val TOKEN = "token"
    }

    private val preferences = context.getSharedPreferences(REFS_NAME, Context.MODE_PRIVATE)

    fun putUser(token: String){
        val editor = preferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun gainUser(): String?{
        val gainToken = preferences.getString(TOKEN, "")
        return gainToken
    }
}