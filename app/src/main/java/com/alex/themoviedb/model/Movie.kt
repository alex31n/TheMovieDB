package com.alex.themoviedb.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
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
    val voteAverage: Float=0.0f,
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int=0,
    @ColumnInfo(name = "overview")
    val overview: String?
): Parcelable{
    @ColumnInfo(name = "term")
    @IgnoredOnParcel
    var term: String?=""
    @IgnoredOnParcel
    var page:Int=1
    @IgnoredOnParcel
    var indexInResponse:Int =-1

}
