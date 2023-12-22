package com.capstone.cultour.data.pref

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.cultour.data.api.explore.RecommendationItem
import com.capstone.cultour.data.api.explore.RecommendedPlaceItem
import com.capstone.cultour.data.api.login.LoginResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("settings")
class UserPreference(context: Context) {

    companion object{
        private const val REFS_NAME = "user_preference"
        private const val USERID = "userId"
        private const val NAME = "name"
        private const val TOKEN = "token"

//        private const val LATITUDE = "latitude"
//        private const val RATING = "rating"
//        private const val CATEGORY = "category"
//        private const val PLACEID = "place_id"
//        private const val LONGITUDE = "longitude"
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

//    fun gainRecommendation(): List<RecommendedPlaceItem>?{
//        val gainPlace = preferences.getString(NAME, "")
//        val gainCategory = preferences.getString(CATEGORY, "")
//        val gainLat = preferences.getString(LATITUDE, "")
//        val gainLong = preferences.getString(LONGITUDE, "")
//        val gainRating = preferences.getString(RATING,"")
//        val gainPlaceId = preferences.getInt(PLACEID, 0)
//        return List<RecommendedPlaceItem(gainLat!!.toDouble(), gainPlace, gainRating!!.toDouble(), gainCategory, gainPlaceId, gainLong!!.toDouble())>
//    }
//
//    fun putRecommendation(recommendedPlaceItem: RecommendedPlaceItem){
//        val editor = preferences.edit()
//        editor.putString(NAME, recommendedPlaceItem.name)
//        editor.putString(CATEGORY, recommendedPlaceItem.category)
//        editor.putString(LATITUDE, recommendedPlaceItem.latitude.toString())
//        editor.putString(LONGITUDE, recommendedPlaceItem.longitude.toString())
//        editor.putString(RATING, recommendedPlaceItem.rating.toString())
//        editor.putString(PLACEID, recommendedPlaceItem.placeId.toString())
//        editor.apply()
//    }

}