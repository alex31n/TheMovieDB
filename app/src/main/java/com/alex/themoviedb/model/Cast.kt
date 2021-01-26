package com.alex.themoviedb.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    val id: Int,
    val name: String,
    @JsonProperty("profile_path")
    val image: String?,
    @JsonProperty("known_for_department")
    val department: String?

) : Parcelable
