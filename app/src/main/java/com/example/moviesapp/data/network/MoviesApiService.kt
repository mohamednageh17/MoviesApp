package com.example.moviesapp.data.network

import com.example.moviesapp.data.MovieResponse
import com.example.moviesapp.data.TrailerResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL="http://api.themoviedb.org/3/tv/"


// TODO: separate retrofit implementation in another file (retrofit provider)
private val retrofit=Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// TODO: pass api key by interceptor(query interceptor)
interface MoviesApiService{
    @GET("popular?")
    fun getPopularMovies(@Query("api_key")apiKey:String="d6464c49a9503b302888a4a139f1ed70"):Single<MovieResponse>

    @GET("top_rated?")
    fun getTopMovies(@Query("api_key")apiKey: String="d6464c49a9503b302888a4a139f1ed70"):Single<MovieResponse>

    @GET("airing_today?")
    fun getAiringTodayMovies(@Query("api_key")apiKey: String="d6464c49a9503b302888a4a139f1ed70"):Single<MovieResponse>

    @GET("on_the_air?")
    fun getOnTheAirMovies(@Query("api_key")apiKey: String="d6464c49a9503b302888a4a139f1ed70"):Single<MovieResponse>

    @GET("latest?api_key=d6464c49a9503b302888a4a139f1ed70")
    fun getLatestMovies():Single<MovieResponse>

    @GET("{id}/videos?api_key=d6464c49a9503b302888a4a139f1ed70")
    fun getMovieTrailer(@Path("id") id: Long?):Single<TrailerResponse>
}

object MoviesApi{
    val retrofitService:MoviesApiService by lazy { retrofit.create(MoviesApiService::class.java) }
}