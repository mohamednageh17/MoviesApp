package com.example.moviesapp.presentation.view.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.presentation.view.adapter.TrailersAdapter
import com.example.moviesapp.domain.model.TrailerModel
import com.example.moviesapp.databinding.ActivityMovieDetailsBinding
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.presentation.Utilis.StateData
import com.example.moviesapp.presentation.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val trailersAdapter by lazy {
        TrailersAdapter(TrailersAdapter.OnItemClickListener {
            onTrailerItemClicked(it)
        })
    }

    lateinit var movie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar()

        movie = (intent.getParcelableExtra("movie") as? MovieModel)!!

        initViews()

        observeUI()

    }

    private fun supportActionBar() {
        val actionbar = supportActionBar
        actionbar!!.title = "Details Activity"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initViews() {

        initTrailersRecyclerView()

        Glide.with(binding.posterImgView)
            .load(MovieModel.posterUrl + movie.posterPath)
            .placeholder(R.drawable.no_image)
            .into(binding.posterImgView)

        binding.overviewTV.text = movie.overview
        binding.firstAirDateTV.text = movie.firstAirDate

        //  movieDetailsViewModel.checkMovieIsFavourite(movie!!.id!!)
    }

    private fun initTrailersRecyclerView() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.trailersRecyclerView)
        binding.trailersRecyclerView.adapter = trailersAdapter
    }

    private fun observeUI() {
        movieDetailsViewModel.isFavourite.observe(this, Observer {
            when (it) {
                true -> markMovieAsFavourite()
                false -> markMovieAsUnFavourite()
            }
        })

        movieDetailsViewModel.fetchTrailers(movie.id!!)
        observeTrailers()
    }

    private fun observeTrailers() {
        movieDetailsViewModel.movieTrailerModel.observe(this) {
            when (it!!.status) {
                StateData.DataStatus.LOADING -> showProgress()
                StateData.DataStatus.SUCCESS -> handleSuccessState(it)
                StateData.DataStatus.ERROR -> handleErrorState(it.error)
                else -> {
                }
            }
        }

    }

    private fun showProgress() {
        binding.progressBar.isVisible = true
    }

    private fun hideProgress() {
        binding.progressBar.isVisible = false
    }

    private fun handleSuccessState(it: StateData<List<TrailerModel>>) {
        hideProgress()
        trailersAdapter.submitList(it.data)
        binding.trailersTextView.text =
            "Trailers: ${it.data!!.size}"
    }

    private fun handleErrorState(it: Throwable?) {
        hideProgress()
        Toast.makeText(this, it!!.message, Toast.LENGTH_LONG).show()
    }

    private fun onTrailerItemClicked(trailerModel: TrailerModel) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=" + trailerModel.key)
            )
        )
    }

    private fun setAsFavourite(movie: MovieModel) {
        movieDetailsViewModel.setAsFavourite(movie)
        Toast.makeText(
            this,
            "Congratulations.. \n You set this movie as a favourite movie ",
            Toast.LENGTH_LONG
        ).show()
    }

     private fun removeMovieFromFavourite(movie: MovieModel){
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

    private fun onFavActionClicked(movie: MovieModel) {
        setAsFavourite(movie)
    }

    private fun onShareActionClicked() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${movie.name} \n ${movie.overview}")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareAction -> onShareActionClicked()
            R.id.favAction -> onFavActionClicked(movie)
        }
        return super.onOptionsItemSelected(item)
    }
}