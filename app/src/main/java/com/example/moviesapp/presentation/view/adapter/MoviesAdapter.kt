package com.example.moviesapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.MovieItemBinding
import com.example.moviesapp.domain.model.MovieModel

class MoviesAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<MovieModel, MoviesAdapter.MovieViewHolder>(ArticlesDiffCallBacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val newsArticle = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(newsArticle)
        }
        holder.bind(getItem(position))
    }

    class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val poster = "https://image.tmdb.org/t/p/w500"
        fun bind(data: MovieModel) {
            binding.root.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.animation)
            Glide.with(binding.moviePosterImg)
                .load(poster + data.backdropPath)
                .placeholder(R.drawable.no_image)
                .into(binding.moviePosterImg)
            binding.movieNameTextView.text = data.name
            binding.movieRatingBar.rating = data.voteAverage!!.toFloat()
            binding.movieDateTextView.text = data.firstAirDate
            binding.movieOriginalLangTextView.text=data.originalLanguage
        }
    }

    class ArticlesDiffCallBacks : DiffUtil.ItemCallback<MovieModel>() {

        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }

    class OnItemClickListener(val clickListener: (newsArticle: MovieModel) -> Unit) {
        fun onClick(newsArticle: MovieModel) {
            clickListener(newsArticle)
        }
    }
}