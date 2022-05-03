package com.example.moviesapp.domain

import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.MovieResponse
import com.example.moviesapp.data.TrailerResponse
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {

    fun getPopularMoviesFromApi(): Single<MovieResponse>

    fun getTopRatedMoviesFromApi(): Single<MovieResponse>

    fun getMovieTrailerFromApi(id:Long?): Single<TrailerResponse>

    fun getOnTheAirMoviesFromApi(): Single<MovieResponse>

    fun getAiringTodayMoviesFromApi(): Single<MovieResponse>

    fun setMovieAsFavourite(movie: Movie): Completable

    fun removeMovieFromFavourite(movie: Movie): Completable

    fun getFavouriteMoviesFromLocalDb(): Single<List<Movie>>

    fun checkMovieIsFavourite(id: Long): Single<List<Movie>>

}