package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.R
import com.example.moviesapp.presentation.Utilis.StateLiveData
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.usecases.FavouriteMoviesUseCase
import com.example.moviesapp.domain.usecases.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val favouriteMoviesUseCase: FavouriteMoviesUseCase
) : BaseViewModel() {

    private var _movieResponse = StateLiveData<List<MovieModel>>()
    val movieResponse: StateLiveData<List<MovieModel>> get() = _movieResponse

    fun loadPopularMovies() {
        fetchMoviesUseCase(R.string.Popular_Movies.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({ _movieResponse.postSuccess(it) }, { _movieResponse.postError(it) })
            .addTo(compositeDisposable)
    }

    fun loadTopMovies() {
        fetchMoviesUseCase(R.string.Top_Rated_Movies.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({ _movieResponse.postSuccess(it) }, { _movieResponse.postError(it) })
            .addTo(compositeDisposable)
    }

    fun loadOnTheAirMovies() {
        fetchMoviesUseCase(R.string.On_The_Air_Movies.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({ _movieResponse.postSuccess(it) }, { _movieResponse.postError(it) })
            .addTo(compositeDisposable)
    }

    fun loadAiringTodayMovies() {
        fetchMoviesUseCase(R.string.Airing_Today_Movies.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({ _movieResponse.postSuccess(it) }, { _movieResponse.postError(it) })
            .addTo(compositeDisposable)
    }

    fun loadFavouriteMovies() {
        favouriteMoviesUseCase.getAllFavouriteMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieResponse.postLoading() }
            .subscribe({ _movieResponse.postSuccess(it) }, { _movieResponse.postError(it) })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
