package com.example.moviesapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.TrailerItemBinding
import com.example.moviesapp.domain.model.TrailerModel

class TrailersAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<TrailerModel, TrailersAdapter.TrailerViewHolder>(ArticlesDiffCallBacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        return TrailerViewHolder(
            TrailerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(trailer)
        }
        holder.bind(getItem(position))
    }

    class TrailerViewHolder(private val binding: TrailerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TrailerModel) {
            binding.trailerNameTextView.text = data.name
        }
    }

    class ArticlesDiffCallBacks : DiffUtil.ItemCallback<TrailerModel>() {

        override fun areItemsTheSame(oldItem: TrailerModel, newItem: TrailerModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrailerModel, newItem: TrailerModel): Boolean {
            return oldItem == newItem
        }
    }

    class OnItemClickListener(val clickListener: (trailerModel: TrailerModel) -> Unit) {
        fun onClick(trailerModel: TrailerModel) {
            clickListener(trailerModel)
        }
    }
}