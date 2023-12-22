package com.capstone.cultour.data.api.explore

data class RecommendationRequest (
    val data: List<RecommendationItem>
)

data class RecommendationItem(
    val user_id: Long,
    val category: String
)