package com.example.moviesapp.presentation.view.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.moviesapp.R
import com.example.moviesapp.presentation.view.adapter.TrailersAdapter
import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.Trailer
import com.example.moviesapp.data.network.MoviesApi
import com.example.moviesapp.data.repository.Repository
import com.example.moviesapp.data.room.MovieDatabase
import com.example.moviesapp.databinding.ActivityMovieDetailsBinding
import com.example.moviesapp.presentation.viewmodel.MovieDetailsViewModel
import com.example.moviesapp.presentation.viewmodel.MovieDetailsViewModelFactory
import com.example.moviesapp.presentation.viewmodel.MovieViewModelFactory
import com.example.moviesapp.presentation.viewmodel.MoviesViewModel


class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    lateinit var repository: Repository
    private lateinit var movieDetailsViewModelFactory: MovieDetailsViewModelFactory
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    lateinit var trailersAdapter: TrailersAdapter

    lateinit var movie:Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

         movie = (intent.getParcelableExtra("movie") as? Movie)!!

        initViewModel()

        observeUI()

        initViews()
    }

    private fun initViewModel() {
        val retrofitService = MoviesApi.retrofitService

        val movieDb = MovieDatabase.getDatabase(this)
        repository = Repository(retrofitService, movieDb)
        movieDetailsViewModelFactory = MovieDetailsViewModelFactory(repository)
        movieDetailsViewModel = ViewModelProvider(this, movieDetailsViewModelFactory)
            .get(MovieDetailsViewModel::class.java)
        binding.movieItem = movie
        binding.lifecycleOwner
        movieDetailsViewModel.getMovieTrailer(movie!!.id)
    }

    private fun initViews( ) {
        binding.favFab.setOnClickListener(View.OnClickListener {
            onFavFabClicked(movie)
        })
        this.title = movie.name
        movieDetailsViewModel.checkMovieIsFavourite(movie!!.id!!)
    }

    private fun observeUI() {
        movieDetailsViewModel.isFavourite.observe(this, Observer {
            when (it) {
                true -> markMovieAsFavourite()
                false -> markMovieAsUnFavourite()
            }
        })

        movieDetailsViewModel.movieTrailer.observe(this, Observer {
            if (it != null) {
                val itemOnClick: (Trailer) -> Unit = { trailer ->
                    binding.trailersRecyclerView.adapter!!.notifyDataSetChanged()
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/watch?v=" + trailer.key)
                        )
                    )
                }
                trailersAdapter = TrailersAdapter(movieDetailsViewModel.movieTrailer.value!!.results!!, itemOnClick)
                val snapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(binding.trailersRecyclerView)
                binding!!.trailersRecyclerView.adapter = trailersAdapter
                binding.trailersTextView.text =
                    "Trailers: ${movieDetailsViewModel.movieTrailer.value!!.results!!.size}"
            }
        })
    }

    private fun onFavFabClicked(movie: Movie) {
        when (movieDetailsViewModel.isFavourite.value) {
            false -> setAsFavourite(movie)
            true -> removeMovieFromFavourite(movie)
        }
    }

    private fun setAsFavourite(movie: Movie){
        movieDetailsViewModel.setAsFavourite(movie)
        Toast.makeText(
            this,
            "Congratulations.. \n You set this movie as a favourite movie ",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun removeMovieFromFavourite(movie: Movie){
        movieDetailsViewModel.removeFromFavourite(movie)
        Toast.makeText(
            this,
            "This movie is removed from your favourite list",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun markMovieAsFavourite() {
        binding.favFab.imageTintList = ColorStateList.valueOf(Color.RED)
    }

    private fun markMovieAsUnFavourite() {
        binding.favFab.imageTintList = ColorStateList.valueOf(Color.WHITE)
    }

    private fun onShareIconClicked(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${movie.name} \n ${movie.overview}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): kotlin.Boolean {
        menuInflater.inflate(R.menu.share_movie_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareAction -> onShareIconClicked()
        }
        return super.onOptionsItemSelected(item)
    }
}