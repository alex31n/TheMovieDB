package com.alex.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val image: String,
    @SerializedName("known_for_department")
    val department: String

) : Parcelable
