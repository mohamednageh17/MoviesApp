package com.example.moviesapp.data.model.remote

import androidx.annotation.Keep
import com.example.moviesapp.domain.model.TrailerModel
import com.google.gson.annotations.SerializedName

@Keep
data class MovieResponse(
    @SerializedName("page")
    var page: Long? = null,
    @SerializedName("results")
    var results: List<Movie>? = null,
    @SerializedName("total_pages")
    var totalPages: Long? = null,
    @SerializedName("total_results")
    var totalResults: Long? = null
)

@Keep
data class Movie(
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("first_air_date")
    var firstAirDate: String? = null,
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_name")
    var originalName: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: Double? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Long? = null,
    @SerializedName("origin_country")
    var originCountry: List<String>? = null,
    @SerializedName("genre_ids")
    var genreIDS: List<Long>? = null,
    var trailerModel: TrailerModel? = null,
)
