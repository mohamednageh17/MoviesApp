package com.example.moviesapp.data.network


import com.example.moviesapp.data.model.remote.MovieResponse
import com.example.moviesapp.data.model.remote.TrailerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface MoviesApiService{
    @GET("popular?")
    fun getPopularMovies():Single<MovieResponse>

    @GET("top_rated?")
    fun getTopMovies():Single<MovieResponse>

    @GET("airing_today?")
    fun getAiringTodayMovies():Single<MovieResponse>

    @GET("on_the_air?")
    fun getOnTheAirMovies():Single<MovieResponse>

    @GET("latest?")
    fun getLatestMovies():Single<MovieResponse>

    @GET("{id}/videos?")
    fun getMovieTrailer(@Path("id") id: Long?):Single<TrailerResponse>
}
