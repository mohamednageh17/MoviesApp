package com.example.moviesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.data.Utilis.StateLiveData
import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.MovieResponse
import com.example.moviesapp.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


private const val TAG = "MainActivityViewModel"

class MoviesViewModel(private val repository: Repository) : BaseViewModel() {

    private var _movieResponse = StateLiveData<MovieResponse>()
    val movieResponse: StateLiveData<MovieResponse> get() = _movieResponse

    var selectedMenuID: Int = -1

    private var _favList = MutableLiveData<List<Movie>?>()
    val favList: LiveData<List<Movie>?> get() = _favList

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        repository.getPopularMoviesFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({
                _movieResponse.postSuccess(it)
            }, {
                _movieResponse.postError(it)
            })

    }


    fun loadTopMovies() {
        repository.getTopRatedMoviesFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({
                _movieResponse.postSuccess(it)
            }, {
                _movieResponse.postError(it)
            })
    }

    fun loadOnTheAirMovies() {
        repository.getOnTheAirMoviesFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({
                _movieResponse.postSuccess(it)
            }, {
                _movieResponse.postError(it)
            })
    }

    fun loadAiringTodayMovies() {
        repository.getAiringTodayMoviesFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({
                _movieResponse.postSuccess(it)
            }, {
                _movieResponse.postError(it)
            })
    }

    fun getFavouriteMovies() {
        repository.getFavouriteMoviesFromLocalDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ moviesList: List<Movie> -> _favList.postValue(moviesList) },
                { error: Throwable -> Log.d(TAG, "loadMovies: Error : $error") })
    }
}

class MovieViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}