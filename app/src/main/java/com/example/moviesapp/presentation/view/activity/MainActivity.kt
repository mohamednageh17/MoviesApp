package com.example.moviesapp.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.R
import com.example.moviesapp.presentation.view.adapter.MoviesAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.MovieResponse
import com.example.moviesapp.data.Utilis.StateData
import com.example.moviesapp.data.network.MoviesApi
import com.example.moviesapp.data.repository.Repository
import com.example.moviesapp.data.room.MovieDatabase
import com.example.moviesapp.presentation.viewmodel.MovieViewModelFactory
import com.example.moviesapp.presentation.viewmodel.MoviesViewModel


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var movieViewModelFactory: MovieViewModelFactory
    private lateinit var moviesAdapter: MoviesAdapter
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        observeUI()
    }

    private fun initViewModel() {
        val retrofitService = MoviesApi.retrofitService
        val movieDB = MovieDatabase.getDatabase(this)
        repository = Repository(retrofitService, movieDB)
        movieViewModelFactory = MovieViewModelFactory(repository)
        moviesViewModel = ViewModelProvider(this, movieViewModelFactory)
            .get(MoviesViewModel::class.java)
    }

    private fun observeUI() {
        val itemOnClick: (Movie) -> Unit = { movie -> onMovieItemClicked(movie) }

        moviesViewModel.movieResponse.observe(this){
            when (it!!.status) {
                StateData.DataStatus.LOADING -> showProgress()
                StateData.DataStatus.SUCCESS -> handleSuccessState(it, itemOnClick)
                StateData.DataStatus.ERROR -> handleErrorState(it)
                else -> {}
            }
        }

        moviesViewModel.favList.observe(this){
            if (it != null) {
                binding.progress.visibility = View.GONE
                moviesAdapter = MoviesAdapter(it, itemOnClick)
                binding.moviesRecyclerView.adapter = moviesAdapter
            }
        }
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }

    private fun handleSuccessState(
        it: StateData<MovieResponse>,
        itemClickListener: (Movie) -> Unit
    ) {
        hideProgress()
        moviesAdapter = MoviesAdapter(it.data!!.results, itemClickListener)
        binding.moviesRecyclerView.adapter = moviesAdapter
    }


    private fun handleErrorState(it: StateData<MovieResponse>) {
        hideProgress()
        showToast(it.error!!.message)
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    private fun onMovieItemClicked(movie: Movie) {
        intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
         if (moviesViewModel.selectedMenuID == R.id.myFavItem)
             moviesViewModel.getFavouriteMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.movie_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuPopulerItem -> {
                showProgress()
                moviesViewModel.loadPopularMovies()
                moviesViewModel.selectedMenuID = R.id.menuPopulerItem
            }
            R.id.menutopRatedItem -> {
                showProgress()
                moviesViewModel.loadTopMovies()
                moviesViewModel.selectedMenuID = R.id.menutopRatedItem
            }
            R.id.menuAiringTodayItem -> {
                showProgress()
                moviesViewModel.loadAiringTodayMovies()
                moviesViewModel.selectedMenuID = R.id.menuAiringTodayItem
            }
            R.id.menuOnTheAirItem -> {
                showProgress()
                moviesViewModel.loadOnTheAirMovies()
                moviesViewModel.selectedMenuID = R.id.menuOnTheAirItem
            }
            R.id.myFavItem -> {
                showProgress()
                moviesViewModel.getFavouriteMovies()
                moviesViewModel.selectedMenuID = R.id.myFavItem
            }
        }
        return super.onOptionsItemSelected(item)
    }
}