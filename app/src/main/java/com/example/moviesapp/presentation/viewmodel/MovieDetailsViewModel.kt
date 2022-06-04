package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.presentation.Utilis.StateLiveData
import com.example.moviesapp.domain.model.Trailer
import com.example.moviesapp.domain.usecases.GetMovieTrailersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val trailerUseCase: GetMovieTrailersUseCase) :
    ViewModel() {
    private var _movieTrailer = StateLiveData<List<Trailer>>()
    val movieTrailer: StateLiveData<List<Trailer>> get() = _movieTrailer

    private var _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean> get() = _isFavourite

    init {

    }

    fun getMovieTrailer(id: Long) {
        trailerUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _movieTrailer.postLoading()
            }
            .subscribe({
                _movieTrailer.postSuccess(it)
            }, {
                _movieTrailer.postError(it)
            })
    }

    /*fun checkMovieIsFavourite(id: Long) {
        repositoryImpl.checkMovieIsFavourite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ moviesList: List<Movie> -> if(moviesList.size>0) _isFavourite.postValue(true) else _isFavourite.postValue(false)},
                { error: Throwable -> Log.d(TAG, "loadMovies: Error : $error") })
    }

    fun setAsFavourite(movie: Movie){
        repositoryImpl.setMovieAsFavourite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({_isFavourite.postValue(true)},{})
    }

    fun removeFromFavourite(movie: Movie){
        repositoryImpl.removeMovieFromFavourite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({_isFavourite.postValue(false)},{})
    }*/
}