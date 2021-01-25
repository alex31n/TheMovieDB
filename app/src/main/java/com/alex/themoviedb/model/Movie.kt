package com.alex.themoviedb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val image: String,
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @SerializedName("original_title")
    @ColumnInfo(name = "title")
    val title: String,
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "overview")
    val overview: String
)
