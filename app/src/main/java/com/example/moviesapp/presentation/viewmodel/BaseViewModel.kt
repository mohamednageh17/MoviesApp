package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel:ViewModel() {
    protected lateinit var compositeDisposable: CompositeDisposable

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}