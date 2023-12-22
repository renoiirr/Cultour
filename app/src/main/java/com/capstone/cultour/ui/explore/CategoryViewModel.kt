package com.capstone.cultour.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.cultour.data.Repository
import com.capstone.cultour.data.Result
import com.capstone.cultour.data.api.explore.RecommendationItem
import com.capstone.cultour.data.api.explore.RecommendationRequest
import com.capstone.cultour.data.api.explore.RecommenderResponse
import com.capstone.cultour.data.api.register.RegisterResponse

class CategoryViewModel(private val repository: Repository) : ViewModel() {
    fun recommendCategory(data: RecommendationRequest) : LiveData<Result<RecommenderResponse>> =
        repository.recommendByCategory(data)
}
