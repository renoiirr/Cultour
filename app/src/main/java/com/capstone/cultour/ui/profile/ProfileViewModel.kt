package com.capstone.cultour.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.cultour.data.Repository

class ProfileViewModel(private val repository: Repository) : ViewModel() {
    private val token = MutableLiveData<String?>()

    fun saveSession(
        token: String, context: Context
    ) = repository.saveSession(token, context)
}