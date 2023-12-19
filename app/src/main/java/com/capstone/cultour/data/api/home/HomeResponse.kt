package com.capstone.cultour.data.api.home

import com.google.gson.annotations.SerializedName

data class HomeResponse(

	@field:SerializedName("places")
	val places: List<PlacesItem>,

	@field:SerializedName("success")
	val success: Boolean
)

data class PlacesItem(

	@field:SerializedName("address_full")
	val addressFull: String,

	@field:SerializedName("image")
	val image: String ,

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("longitude")
	val longitude: Double,

	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("place_id")
	val placeId: Int
)
