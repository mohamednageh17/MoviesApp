package com.example.moviesapp.data.mapper

import com.example.moviesapp.data.model.remote.Movie
import com.example.moviesapp.data.model.remote.MovieResponse
import com.example.moviesapp.data.model.remote.TrailerResponse
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.model.Trailer

fun MovieResponse.mapToDomain()=this.results?.map {
    MovieModel(
        id=it.id,
        name = it.name,
        backdropPath = it.backdropPath,
        firstAirDate = it.firstAirDate,
        originalLanguage = it.originalLanguage,
        originalName = it.originalName,
        overview = it.overview,
        popularity = it.popularity,
        posterPath = it.posterPath,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount
    )
}

fun TrailerResponse.mapToDomain()=this.results?.map{
    Trailer(
        iso639_1 = it.iso639_1,
        iso3166_1 = it.iso3166_1,
        name =it.name,
        key = it.key,
        site = it.site,
        size = it.size,
        type = it.type,
        official = it.official,
        publishedAt = it.publishedAt,
        id=it.id
    )
}