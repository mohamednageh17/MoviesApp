package com.example.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesapp.data.model.local.MovieEntity
import com.example.moviesapp.domain.model.MovieModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MovieDao {
    @Insert
    fun insert(movie:MovieEntity):Completable

    @Delete
    fun delete(movie: MovieEntity):Completable

    @Query("delete from fav_movie")
    fun deleteAllFavouriteMovies()

    @Query("select * from fav_movie")
    fun getAllFavouriteMovies():Single<List<MovieEntity>>

    @Query("select * from fav_movie where id=:id")
    fun checkMovieIsFavourite(id:Long):Single<MovieEntity>
}