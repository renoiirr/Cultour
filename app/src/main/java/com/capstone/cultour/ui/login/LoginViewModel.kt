package com.capstone.cultour.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.cultour.data.Repository

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val token = MutableLiveData<String?>()

    fun loginUser(
        email: String, password: String
    ) = repository.loginUser(email, password)


    fun getSession(
        context: Context
    ): LiveData<String?> {
        val DataToken = repository.getSession(context)
        token.value = DataToken
        return token
    }

    fun saveSession(
        token: String, context: Context
    ) = repository.saveSession(token, context)
}