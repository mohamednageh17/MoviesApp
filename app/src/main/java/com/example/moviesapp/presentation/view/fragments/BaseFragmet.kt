package com.example.moviesapp.presentation.view.fragments

import android.content.Intent
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.presentation.Utilis.StateData
import com.example.moviesapp.presentation.view.activity.MovieDetailsActivity
import com.example.moviesapp.presentation.view.adapter.MoviesAdapter
import com.example.moviesapp.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
abstract class BaseFragmet:Fragment() {

    val moviesViewModel: MoviesViewModel by viewModels()

    val adapter by lazy { MoviesAdapter(MoviesAdapter.OnItemClickListener {
        onMovieItemClicked(it)
    }) }

    fun moviesObserver() {
        moviesViewModel.movieResponse.observe(this){
            when (it!!.status) {
                StateData.DataStatus.LOADING -> showProgress()
                StateData.DataStatus.SUCCESS -> handleSuccessState(it)
                StateData.DataStatus.ERROR -> handleErrorState(it.error)
                else -> {}
            }
        }
    }

    private var progressBar: ProgressBar?=null

    fun setProgress(progressBar: ProgressBar){
        this.progressBar=progressBar
    }

    private fun showProgress() {
        progressBar!!.isVisible = true
    }

    private fun hideProgress() {
        progressBar!!.isVisible = false
    }

    private fun handleSuccessState(it: StateData<List<MovieModel>>) {
        hideProgress()
        adapter.submitList(it.data)
    }

    private fun handleErrorState(it:Throwable?) {
        hideProgress()
        Toast.makeText(requireContext(),it!!.message, Toast.LENGTH_LONG).show()
    }

    private fun onMovieItemClicked(movie: MovieModel) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    abstract fun initRecyclerView()
}