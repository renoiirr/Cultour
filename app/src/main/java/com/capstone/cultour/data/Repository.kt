package com.capstone.cultour.data

import android.content.Context
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.capstone.cultour.data.api.home.HomeResponse
import com.capstone.cultour.data.api.home.PlacesItem
import com.capstone.cultour.data.api.login.Login
import com.capstone.cultour.data.api.login.LoginResponse
import com.capstone.cultour.data.api.register.Register
import com.capstone.cultour.data.api.register.RegisterResponse
import com.capstone.cultour.data.pref.UserModel
import com.capstone.cultour.data.pref.UserPreference
import com.capstone.cultour.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject
import retrofit2.HttpException
import java.util.prefs.Preferences

class Repository private constructor(
    private val apiService: ApiService,
    private val pref: UserPreference
) {
    suspend fun registerUser(
        username: String,
        email: String,
        password: String
    ): Result<RegisterResponse> {
        return try {
            val response = apiService.signup(Register(username, email, password))
            Result.Success(response)
        } catch (e: HttpException) {
            val error = e.response()?.errorBody()?.string()

            val jsonObject = JSONObject(error!!)
            val errorMessage = jsonObject.getString("message")
            Result.Error(errorMessage)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    fun loginUser(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(Login(email, password))
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }


    fun saveSession(token: String, context: Context) {
        val settingPreferences = UserPreference(context)
        return settingPreferences.putUser(token)
    }

    fun getSession(context: Context): String? {
        val settingPreferences = UserPreference(context)
        return settingPreferences.gainUser()
    }

    fun getPlaces(): LiveData<Result<HomeResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getPlaces()
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,
            pref: UserPreference
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService, pref)
            }.also { instance = it }
    }
}
