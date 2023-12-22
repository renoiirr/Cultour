package com.capstone.cultour.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.cultour.data.Repository
import com.capstone.cultour.data.api.login.LoginResult

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val token = MutableLiveData<LoginResult?>()

    fun loginUser(
        email: String, password: String
    ) = repository.loginUser(email, password)


    fun getSession(
        context: Context
    ): LiveData<LoginResult?> {
        val DataToken = repository.getSession(context)
        token.value = DataToken
        return token
    }

    fun saveSession(
        token: LoginResult, context: Context
    ) = repository.saveSession(token, context)

}