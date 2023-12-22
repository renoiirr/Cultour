package com.capstone.cultour.data.api.explore

import com.google.gson.annotations.SerializedName

data class RecommenderResponse(

	@field:SerializedName("Recommended Place")
	val recommendedPlace: List<RecommendedPlaceItem> ,

	@field:SerializedName("message")
	val message: String? = null
)

data class RecommendedPlaceItem(

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("place_id")
	val placeId: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double
)
