package com.example.moviesapp.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.data.TrailerResponse
import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val TAG = "DetailsActivityViewMode"
class MovieDetailsViewModel(private val repository: Repository):ViewModel() {
    private var _movieTrailer = MutableLiveData<TrailerResponse>()
    val movieTrailer: LiveData<TrailerResponse> get() = _movieTrailer

    private var _isFavourite= MutableLiveData<Boolean>()
    val isFavourite:LiveData<Boolean>get() = _isFavourite

    init {
        Log.d(TAG, "nageh View model created ... : ")
    }

    fun getMovieTrailer(id:Long?) {
        repository.getMovieTrailerFromApi(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: TrailerResponse -> _movieTrailer.value = response },
                { error: Throwable -> Log.d(TAG, "loadMovieTrailer: Error : $error") }
            )
    }

    fun checkMovieIsFavourite(id: Long) {
        repository.checkMovieIsFavourite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ moviesList: List<Movie> -> if(moviesList.size>0) _isFavourite.postValue(true) else _isFavourite.postValue(false)},
                { error: Throwable -> Log.d(TAG, "loadMovies: Error : $error") })
    }

    fun setAsFavourite(movie: Movie){
        repository.setMovieAsFavourite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({_isFavourite.postValue(true)},{})
    }

    fun removeFromFavourite(movie: Movie){
        repository.removeMovieFromFavourite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({_isFavourite.postValue(false)},{})
    }
}

class MovieDetailsViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDetailsViewModel::class.java))
            return MovieDetailsViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}