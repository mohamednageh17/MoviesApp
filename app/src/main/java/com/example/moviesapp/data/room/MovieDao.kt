package com.example.moviesapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesapp.data.Movie
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Insert
    fun insert(movie: Movie):Completable

    @Delete
    fun delete(movie: Movie):Completable

    @Query("delete from fav_movie")
    fun deleteAllFavouriteMovies()

    @Query("select * from fav_movie")
    fun getAllFavouriteMovies():Single<List<Movie>>

    @Query("select * from fav_movie where id=:id")
    fun checkMovieIsFavourite(id:Long):Single<List<Movie>>
}