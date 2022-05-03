package com.example.moviesapp.presentation.view.binding

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviesapp.R

val poster = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("loadPosterImg")
fun loadPosterImg(posterImg: ImageView, url:String){
    Glide.with(posterImg)
        .load(poster+url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.drawable.loadingicon)
        .into(posterImg)
}

@BindingAdapter("firstAirDate")
fun firstAirDate(textView: TextView,airDate:String){
    textView.text= "$airDate"
}

@BindingAdapter("voreAvarage")
fun voreAvarage(ratingBar: RatingBar,rate:Double){
    ratingBar.rating= (rate/2).toFloat()
}

