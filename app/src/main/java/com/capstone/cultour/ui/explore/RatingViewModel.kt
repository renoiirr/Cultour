package com.capstone.cultour.ui.explore

import androidx.lifecycle.ViewModel
import com.capstone.cultour.data.Repository

class RatingViewModel(private val repository: Repository) : ViewModel() {
    fun getPlaces() = repository.getPlaces()
}