package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.presentation.Utilis.StateLiveData
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.usecases.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(val fetchMoviesUseCase: FetchMoviesUseCase) : BaseViewModel() {

    private var _movieResponse = StateLiveData<List<MovieModel>>()
    val movieResponse: StateLiveData<List<MovieModel>> get() = _movieResponse

    var selectedMenuID: Int = -1

    private var _favList = MutableLiveData<List<MovieModel>>()
    val favList: LiveData<List<MovieModel>> get() = _favList

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        fetchMoviesUseCase("popular")
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
        fetchMoviesUseCase("top_rated")
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
        fetchMoviesUseCase("on_the_air")
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
        fetchMoviesUseCase("airing_today")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({
                _movieResponse.postSuccess(it)
            }, {
                _movieResponse.postError(it)
            })
    }

   /* fun getFavouriteMovies() {
        repositoryImpl.getFavouriteMoviesFromLocalDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ moviesList: List<Movie> -> _favList.postValue(moviesList) },
                { error: Throwable -> Log.d(TAG, "loadMovies: Error : $error") })
    }*/
}
