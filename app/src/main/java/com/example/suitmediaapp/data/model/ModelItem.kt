package com.example.suitmediaapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GuestItem(
	@SerializedName("birthdate")
	var birthdate: String? = null,

	@SerializedName("name")
	var name: String? = null,

	@SerializedName("id")
	var id: Int? = 0
): Parcelable

@Parcelize
data class Event(
	var name: String = "",
	var date: String = "",
	var image: Int = 0,
	var lat: String = "",
	var lng: String = ""
): Parcelable
