package com.alex.themoviedb.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class Movie(
    @JsonProperty("id")
    @ColumnInfo(name = "movie_id")
    var movieId: Int=0,
    @JsonProperty("poster_path")
    @ColumnInfo(name = "poster_path")
    var image: String?,
    @JsonProperty("release_date")
    @ColumnInfo(name = "release_date")
    var releaseDate: String?,
    @JsonProperty("original_title")
    @ColumnInfo(name = "title")
    var title: String?,
    @JsonProperty("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Float=0.0f,
    @JsonProperty("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount: Int=0,
    @ColumnInfo(name = "overview")
    var overview: String?
): Parcelable{
    @PrimaryKey(autoGenerate = true)
    @JsonIgnore
    var id: Int=0
    @ColumnInfo(name = "term")
    @IgnoredOnParcel
    var term: String?=""
    @IgnoredOnParcel
    var page:Int=1
    @IgnoredOnParcel
    var indexInResponse:Int =-1

}
