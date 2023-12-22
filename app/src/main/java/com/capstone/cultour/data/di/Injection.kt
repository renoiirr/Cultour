package com.capstone.cultour.data.di

import android.content.Context
import com.capstone.cultour.data.Repository
import com.capstone.cultour.data.pref.UserPreference
import com.capstone.cultour.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference(context)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService, pref)
    }
}