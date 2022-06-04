package com.example.moviesapp.domain.repositories

import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.model.Trailer
import io.reactivex.Single

interface MoviesRepository {

    fun fetchMoviesFromApi(type:String): Single<List<MovieModel>>
    fun getMovieTrailers(id:Long):Single<List<Trailer>>
    /*
    fun setMovieAsFavourite(movie: Movie): Completable

    fun removeMovieFromFavourite(movie: Movie): Completable

    fun getFavouriteMoviesFromLocalDb(): Single<List<Movie>>
    */


}