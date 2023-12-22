package com.capstone.cultour.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.cultour.data.Repository
import com.capstone.cultour.data.api.login.LoginResult

class ProfileViewModel(private val repository: Repository) : ViewModel() {
    private val token = MutableLiveData<LoginResult?>()

    fun saveSession(
        token: LoginResult, context: Context
    ) = repository.saveSession(token, context)
}