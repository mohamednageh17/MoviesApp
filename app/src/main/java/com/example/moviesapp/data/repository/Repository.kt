package com.example.moviesapp.data.repository

import com.example.moviesapp.data.MovieResponse
import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.TrailerResponse
import com.example.moviesapp.data.network.MoviesApiService
import com.example.moviesapp.data.room.MovieDatabase
import io.reactivex.Completable
import io.reactivex.Single


class Repository(private val retrofit:MoviesApiService,private val movieDB: MovieDatabase) {

    fun getPopularMoviesFromApi():Single<MovieResponse>{
        return retrofit.getPopularMovies()
    }

    fun getTopRatedMoviesFromApi():Single<MovieResponse>{
        return retrofit.getTopMovies()
    }

    fun getMovieTrailerFromApi(id:Long?):Single<TrailerResponse>{
        return retrofit.getMovieTrailer(id)
    }

    fun getOnTheAirMoviesFromApi():Single<MovieResponse>{
        return retrofit.getOnTheAirMovies()
    }

    fun getAiringTodayMoviesFromApi():Single<MovieResponse>{
        return retrofit.getAiringTodayMovies()
    }

    fun setMovieAsFavourite(movie: Movie):Completable{
        return movieDB.movieDao().insert(movie)
    }

    fun getFavouriteMoviesFromLocalDb():Single<List<Movie>>{
        return movieDB.movieDao().getAllFavouriteMovies()
    }

    fun checkMovieIsFavourite(id: Long):Single<List<Movie>>{
        return movieDB.movieDao().checkMovieIsFavourite(id)
    }

    fun removeMovieFromFavourite(movie: Movie):Completable{
        return movieDB.movieDao().delete(movie)
    }
}