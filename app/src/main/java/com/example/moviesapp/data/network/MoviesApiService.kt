package com.example.moviesapp.data.network


import com.example.moviesapp.data.model.remote.MovieResponse
import com.example.moviesapp.data.model.remote.TrailerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApiService{
    @GET("popular?")
    fun getPopularMovies(@Query("page") page:Int=1):Single<MovieResponse>

    @GET("top_rated?")
    fun getTopMovies():Single<MovieResponse>

    @GET("airing_today?")
    fun getAiringTodayMovies():Single<MovieResponse>

    @GET("on_the_air?")
    fun getOnTheAirMovies():Single<MovieResponse>

    @GET("{id}/videos?")
    fun getMovieTrailer(@Path("id") id: Long?):Single<TrailerResponse>
}
