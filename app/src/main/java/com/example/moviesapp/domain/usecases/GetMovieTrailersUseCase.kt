package com.example.moviesapp.domain.usecases

import com.example.moviesapp.domain.repositories.MoviesRepository
import javax.inject.Inject

class GetMovieTrailersUseCase @Inject constructor(val moviesRepository: MoviesRepository) {
    operator fun invoke(id:Long)=moviesRepository.getMovieTrailers(id)
}