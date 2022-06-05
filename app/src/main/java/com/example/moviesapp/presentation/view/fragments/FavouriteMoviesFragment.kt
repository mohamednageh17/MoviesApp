package com.example.moviesapp.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapp.databinding.FavouriteMoviesFragmentBinding
import com.example.moviesapp.presentation.view.fragments.BaseFragmet

class FavouriteMoviesFragment :BaseFragmet(){

    private lateinit var binding: FavouriteMoviesFragmentBinding

    override fun initRecyclerView() {
        binding.recyclerView.adapter=adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FavouriteMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProgress(binding.progressBar)
        moviesViewModel.loadFavouriteMovies()

        initRecyclerView()
        moviesObserver()

    }
}