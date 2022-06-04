package com.example.moviesapp.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.moviesapp.R
import com.example.moviesapp.presentation.view.adapter.MoviesAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.presentation.Utilis.StateData
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val moviesViewModel: MoviesViewModel by viewModels()

    private val adapter by lazy { MoviesAdapter(MoviesAdapter.OnItemClickListener {
        onMovieItemClicked(it)
    }) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        observeUI()
    }

    fun initUI(){
        binding.recyclerView.adapter=adapter
    }

    private fun observeUI() {

        moviesViewModel.movieResponse.observe(this){
            when (it!!.status) {
                StateData.DataStatus.LOADING -> showProgress()
                StateData.DataStatus.SUCCESS -> handleSuccessState(it)
                StateData.DataStatus.ERROR -> handleErrorState(it.error)
                else -> {}
            }
        }

      /*  moviesViewModel.favList.observe(this){
            if (it != null) {
                binding.progress.visibility = View.GONE
                moviesAdapter = MoviesAdapter(it, itemOnClick)
                binding.moviesRecyclerView.adapter = moviesAdapter
            }
        }*/
    }

    private fun showProgress() {
        binding.progressBar.isVisible = true
    }

    private fun hideProgress() {
        binding.progressBar.isVisible = false
    }

    private fun handleSuccessState(it: StateData<List<MovieModel>>) {
        hideProgress()
        adapter.submitList(it.data)
    }

    private fun handleErrorState(it: Throwable?) {
        hideProgress()
        Toast.makeText(this, it!!.message, Toast.LENGTH_LONG).show()
    }

    private fun onMovieItemClicked(movie: MovieModel) {
        intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        /* if (moviesViewModel.selectedMenuID == R.id.myFavItem)
             moviesViewModel.getFavouriteMovies()*/
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
               // moviesViewModel.getFavouriteMovies()
                moviesViewModel.selectedMenuID = R.id.myFavItem
            }
        }
        return super.onOptionsItemSelected(item)
    }
}