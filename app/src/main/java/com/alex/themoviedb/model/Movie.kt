package com.alex.themoviedb.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int=0,
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val image: String?,
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @SerializedName("original_title")
    @ColumnInfo(name = "title")
    val title: String?,
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double=0.0,
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int=0,
    @ColumnInfo(name = "overview")
    val overview: String?
){
    @ColumnInfo(name = "term")
    var term: String?=""
    var page:Int=1
    var indexInResponse:Int =-1
}
