package com.example.moviesapp.data.model.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "fav_movie")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Long? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,
    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null,
    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null,
    @ColumnInfo(name = "original_name")
    var originalName: String? = null,
    @ColumnInfo(name = "overview")
    var overview: String? = null,
    @ColumnInfo(name = "popularity")
    var popularity: Double? = null,
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count")
    var voteCount: Long? = null,
    @Ignore
    var isFavourite: Boolean = false
) : Parcelable