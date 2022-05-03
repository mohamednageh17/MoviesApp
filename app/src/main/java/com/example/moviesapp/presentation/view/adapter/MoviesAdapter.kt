package com.example.moviesapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.MovieItemBinding
import com.example.moviesapp.data.Movie

class MoviesAdapter(private var moviesList: List<Movie>?, private val itemClickListener: (Movie) -> Unit): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    // TODO: use function to submit list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie=moviesList!![position]

        holder.bind(movie,itemClickListener)
    }

    override fun getItemCount() = moviesList!!.size

    class MoviesViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, itemClickListener:(Movie)->Unit){
            binding.movieItem=movie
            binding.executePendingBindings()
            binding.root.setOnClickListener { itemClickListener(movie) }
        }
    }
}
