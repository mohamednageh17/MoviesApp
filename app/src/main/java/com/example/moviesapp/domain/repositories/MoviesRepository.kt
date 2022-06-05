package com.example.moviesapp.domain.repositories

import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.model.TrailerModel
import io.reactivex.Completable
import io.reactivex.Single

interface MoviesRepository {

    fun fetchMoviesFromApi(type:String): Single<List<MovieModel>>

    fun getMovieTrailers(id:Long):Single<List<TrailerModel>>

    fun setMovieAsFavourite(movie: MovieModel): Completable

    fun removeMovieFromFavourite(movie: MovieModel): Completable

    fun getFavouriteMoviesFromLocalDb(): Single<List<MovieModel>>


}