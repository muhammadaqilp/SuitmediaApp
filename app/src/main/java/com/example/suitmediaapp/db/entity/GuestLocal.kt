package com.example.suitmediaapp.db.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GuestLocal(
    var id: Int = 0,
    var name: String? = null,
    var birthdate: String? = null,
): Parcelable
