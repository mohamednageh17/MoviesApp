package com.example.moviesapp.domain.usecases

import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.repositories.MoviesRepository
import javax.inject.Inject

class FavouriteMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    open fun setMovieAsFavourite(movie:MovieModel)=moviesRepository.setMovieAsFavourite(movie)

    open fun removeMoviesFromFavourite(movie: MovieModel)=moviesRepository.removeMovieFromFavourite(movie)

    open fun getAllFavouriteMovies()=moviesRepository.getFavouriteMoviesFromLocalDb()
}