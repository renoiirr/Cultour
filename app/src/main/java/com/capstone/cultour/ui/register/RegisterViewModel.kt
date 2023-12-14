package com.capstone.cultour.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.cultour.data.Repository
import com.capstone.cultour.data.Result
import com.capstone.cultour.data.api.register.RegisterResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(private val repository: Repository): ViewModel() {
    private val liveDataResult = MutableLiveData<Result<RegisterResponse>>()
    fun registerUser(
        username: String,
        email: String,
        password: String

    ): LiveData<Result<RegisterResponse>> {
        viewModelScope.launch {
            val result = repository.registerUser(username, email, password)
            liveDataResult.value = result
        }
        return liveDataResult
    }

}