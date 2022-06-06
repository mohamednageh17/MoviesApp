package com.example.moviesapp.data.repository

import com.example.moviesapp.R
import com.example.moviesapp.data.network.MoviesApiService
import com.example.moviesapp.data.local.MovieDatabase
import com.example.moviesapp.data.mapper.mapToDomain
import com.example.moviesapp.data.mapper.mapToEntity
import com.example.moviesapp.data.model.remote.MovieResponse
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.model.TrailerModel
import com.example.moviesapp.domain.repositories.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val moviesApiService:MoviesApiService,
                                         private val movieDB: MovieDatabase):MoviesRepository {

    override fun fetchMoviesFromApi(type: String): Single<List<MovieModel>> {
        lateinit var moviewResponse:Single<MovieResponse>
        when(type){
            R.string.Popular_Movies.toString()->moviewResponse= moviesApiService.getPopularMovies()
            R.string.Top_Rated_Movies.toString()->moviewResponse= moviesApiService.getTopMovies()
            R.string.Airing_Today_Movies.toString()->moviewResponse= moviesApiService.getAiringTodayMovies()
            R.string.On_The_Air_Movies.toString()->moviewResponse= moviesApiService.getOnTheAirMovies()
        }
        return moviewResponse.map {
            it.mapToDomain()
        }
    }

    override fun getMovieTrailers(id: Long): Single<List<TrailerModel>> {
        return moviesApiService.getMovieTrailer(id).map {
            it.mapToDomain()
        }
    }

    override fun setMovieAsFavourite(movie: MovieModel): Completable {
        return movieDB.movieDao().insert(movie.mapToEntity())
    }

    override fun removeMovieFromFavourite(movie: MovieModel): Completable {
        return movieDB.movieDao().delete(movie.mapToEntity())
    }

    override fun getFavouriteMoviesFromLocalDb(): Single<List<MovieModel>> {
        return movieDB.movieDao().getAllFavouriteMovies().map {
            it.mapToDomain()
        }
    }

    override fun checkIfMovieIsAfavourite(id: Long): Single<MovieModel> {
        return movieDB.movieDao().checkMovieIsFavourite(id).map {
            it.mapToDomain()
        }
    }
}