package com.example.moviesapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.TrailerItemBinding
import com.example.moviesapp.data.Trailer

class TrailersAdapter(private val trailersList:List<Trailer>,
                      private val itemClickListener: (Trailer) -> Unit):
    RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        return TrailerViewHolder(
            TrailerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(trailersList[position],itemClickListener)
    }

    override fun getItemCount()=trailersList.size

    class TrailerViewHolder(val binding:TrailerItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(trailer: Trailer, itemClickListener:(Trailer)->Unit){
            binding.trailerItem=trailer
            binding.executePendingBindings()
            binding.root.setOnClickListener { itemClickListener(trailer) }
        }
    }
}