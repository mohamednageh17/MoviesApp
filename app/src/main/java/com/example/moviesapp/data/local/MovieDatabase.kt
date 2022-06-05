package com.example.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp.data.model.local.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase:RoomDatabase() {

    abstract fun movieDao():MovieDao

}