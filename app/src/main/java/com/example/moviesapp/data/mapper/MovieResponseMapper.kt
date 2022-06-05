package com.example.moviesapp.data.mapper

import com.example.moviesapp.data.model.local.MovieEntity
import com.example.moviesapp.data.model.remote.MovieResponse
import com.example.moviesapp.data.model.remote.TrailerResponse
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.model.TrailerModel

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
    TrailerModel(
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


fun MovieModel.mapToEntity():MovieEntity{
    return MovieEntity(
        id=this.id,
        name = this.name,
        backdropPath = this.backdropPath,
        firstAirDate = this.firstAirDate,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun MovieEntity.mapToDomain():MovieModel{
    return MovieModel(
        id=this.id,
        name = this.name,
        backdropPath = this.backdropPath,
        firstAirDate = this.firstAirDate,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun List<MovieEntity>.mapToDomain() :List<MovieModel>{
    return this.map {
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
}