package com.example.moviesapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val id: Long? = null,
    val name: String? = null,
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Long? = null,
    val originCountry: List<String>? = null,
    val genreIDS: List<Long>? = null,
    val trailerModel: TrailerModel? = null,
    val isFavourite: Boolean = false,
) : Parcelable{
    companion object{
        val posterUrl="https://image.tmdb.org/t/p/w500"
    }
}

@Parcelize
data class TrailerModel(
    val iso639_1: String? = null,
    val iso3166_1: String? = null,
    val name: String? = null,
    val key: String? = null,
    val site: String? = null,
    val size: Long? = null,
    val type: String? = null,
    val official: Boolean? = null,
    val publishedAt: String? = null,
    val id: String? = null,
) : Parcelable

