package com.capstone.cultour.data.retrofit

import com.capstone.cultour.data.api.login.Login
import com.capstone.cultour.data.api.login.LoginResponse
import com.capstone.cultour.data.api.register.Register
import com.capstone.cultour.data.api.register.RegisterResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun signup(
        @Body requestBody: Register
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body requestBody: Login
    ): LoginResponse
}