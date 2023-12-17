package com.capstone.cultour.data.api.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class HomeResponse(

	@field:SerializedName("places")
	val places: List<PlacesItem>,

	@field:SerializedName("success")
	val success: Boolean? = null
)

@Parcelize
data class PlacesItem(

	@field:SerializedName("address_full")
	val addressFull: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("_id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
):Parcelable
