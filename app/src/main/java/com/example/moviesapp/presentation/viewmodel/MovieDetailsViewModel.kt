package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.presentation.Utilis.StateLiveData
import com.example.moviesapp.domain.model.TrailerModel
import com.example.moviesapp.domain.usecases.FavouriteMoviesUseCase
import com.example.moviesapp.domain.usecases.GetMovieTrailersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val trailerUseCase: GetMovieTrailersUseCase,
    private val favouriteMoviesUseCase: FavouriteMoviesUseCase
) : ViewModel() {

    private var _movieTrailer = StateLiveData<List<TrailerModel>>()
    val movieTrailerModel: StateLiveData<List<TrailerModel>> get() = _movieTrailer

    private var _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean> get() = _isFavourite

    fun fetchTrailers(id: Long) {
        trailerUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieTrailer.postLoading() }
            .subscribe({ _movieTrailer.postSuccess(it) }, { _movieTrailer.postError(it) }
            )
    }

    fun setAsFavourite(movie: MovieModel){
        favouriteMoviesUseCase.setMovieAsFavourite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({_isFavourite.postValue(true)},{})
    }

    fun checkMovieIsFavourite(id: Long) {
        favouriteMoviesUseCase.checkIfMovieIsAfavourite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       if(it!=null) _isFavourite.postValue(true) else _isFavourite.postValue(false)
            }, {
                _isFavourite.postValue(false)
            })
    }

    fun removeFromFavourite(movie: MovieModel){
        favouriteMoviesUseCase.removeMoviesFromFavourite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({_isFavourite.postValue(false)},{})
    }
}