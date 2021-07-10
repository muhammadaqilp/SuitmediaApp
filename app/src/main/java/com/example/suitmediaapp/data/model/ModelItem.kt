package com.example.suitmediaapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GuestItem(
	@SerializedName("birthdate")
	val birthdate: String?,

	@SerializedName("name")
	val name: String?,

	@SerializedName("id")
	val id: Int?
): Parcelable

@Parcelize
data class Event(
	var name: String = "",
	var date: String = "",
	var image: Int = 0
): Parcelable
