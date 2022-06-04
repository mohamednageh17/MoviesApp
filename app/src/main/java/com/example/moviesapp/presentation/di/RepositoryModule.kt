package com.example.moviesapp.presentation.di

import com.example.moviesapp.data.local.MovieDatabase
import com.example.moviesapp.data.network.MoviesApiService
import com.example.moviesapp.data.repository.RepositoryImpl
import com.example.moviesapp.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(moviesApiService: MoviesApiService, movieDB: MovieDatabase):MoviesRepository{
        return RepositoryImpl(moviesApiService,movieDB)
    }
}