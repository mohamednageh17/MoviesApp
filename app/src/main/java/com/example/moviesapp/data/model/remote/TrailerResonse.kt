package com.example.moviesapp.data.model.remote

import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("results")
    val results: List<Trailer>? = null
)

data class Trailer(
    @SerializedName("iso639_1")
    val iso639_1: String? = null,
    @SerializedName("iso3166_1")
    val iso3166_1: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("site")
    val site: String? = null,
    @SerializedName("size")
    val size: Long? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("official")
    val official: Boolean? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("id")
    val id: String? = null
)