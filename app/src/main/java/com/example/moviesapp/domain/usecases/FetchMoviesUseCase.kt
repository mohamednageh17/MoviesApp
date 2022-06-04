package com.example.moviesapp.domain.usecases

import com.example.moviesapp.domain.repositories.MoviesRepository
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    operator fun invoke(type:String)=moviesRepository.fetchMoviesFromApi(type)
}