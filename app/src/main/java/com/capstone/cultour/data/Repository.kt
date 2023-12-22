package com.capstone.cultour.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.cultour.data.api.explore.RecommendationItem

import com.capstone.cultour.data.api.explore.RecommendationRequest
import com.capstone.cultour.data.api.explore.RecommendedPlaceItem
import com.capstone.cultour.data.api.explore.RecommenderResponse

import com.capstone.cultour.data.api.home.HomeResponse
import com.capstone.cultour.data.api.login.Login
import com.capstone.cultour.data.api.login.LoginResponse
import com.capstone.cultour.data.api.login.LoginResult
import com.capstone.cultour.data.api.register.Register
import com.capstone.cultour.data.api.register.RegisterResponse
import com.capstone.cultour.data.pref.UserPreference
import com.capstone.cultour.data.retrofit.ApiService
import org.json.JSONObject
import retrofit2.HttpException

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

    fun saveSession(token: LoginResult, context: Context) {
        val settingPreferences = UserPreference(context)
        return settingPreferences.putUser(token)
    }


    fun getSession(context: Context): LoginResult? {
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


    fun recommendByCategory(
        request: RecommendationRequest
    ): LiveData<Result<RecommenderResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.recommendByCategory(request)
            emit(Result.Success(response))
        }catch (e: Exception){
            Log.e("Repository", "Error in recommendByCategory", e)
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
