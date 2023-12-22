package com.capstone.cultour.data.retrofit


import com.capstone.cultour.data.api.explore.RecommendationItem
import com.capstone.cultour.data.api.explore.RecommendationRequest
import com.capstone.cultour.data.api.explore.RecommenderResponse
import com.capstone.cultour.data.api.home.HomeResponse
import com.capstone.cultour.data.api.login.Login
import com.capstone.cultour.data.api.login.LoginResponse
import com.capstone.cultour.data.api.register.Register
import com.capstone.cultour.data.api.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
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

    @GET("home")
    suspend fun getPlaces(): HomeResponse

    @POST("recommend")
    suspend fun recommendByCategory(
    @Body requestBody: RecommendationRequest
    ): RecommenderResponse


}