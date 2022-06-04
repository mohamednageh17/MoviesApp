package com.example.moviesapp.data.repository

import com.example.moviesapp.data.network.MoviesApiService
import com.example.moviesapp.data.local.MovieDatabase
import com.example.moviesapp.data.mapper.mapToDomain
import com.example.moviesapp.data.model.remote.MovieResponse
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.model.Trailer
import com.example.moviesapp.domain.repositories.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val moviesApiService:MoviesApiService,
                                         private val movieDB: MovieDatabase):MoviesRepository {

    override fun fetchMoviesFromApi(type: String): Single<List<MovieModel>> {
        lateinit var moviewResponse:Single<MovieResponse>
        when(type){
            "popular"->moviewResponse= moviesApiService.getPopularMovies()
            "top_rated"->moviewResponse= moviesApiService.getTopMovies()
            "airing_today"->moviewResponse= moviesApiService.getAiringTodayMovies()
            "on_the_air"->moviewResponse= moviesApiService.getOnTheAirMovies()
            "latest"->moviewResponse= moviesApiService.getLatestMovies()
        }
        return moviewResponse.map {
            it.mapToDomain()
        }
    }

    override fun getMovieTrailers(id: Long): Single<List<Trailer>> {
        return moviesApiService.getMovieTrailer(id).map {
            it.mapToDomain()
        }
    }

/*    fun getTopRatedMoviesFromApi():Single<MovieResponse>{
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
    }*/
}