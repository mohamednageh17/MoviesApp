package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.data.room.MovieDatabase
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// TODO: read about app multidex

@HiltAndroidApp
class MoviesApp : Application() {
}