package com.capstone.cultour.data

import androidx.lifecycle.ViewModel

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun getPlaces() = repository.getPlaces()
}