package com.example.moviesapp.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapp.databinding.PopularMoviesFragmentBinding
import com.example.moviesapp.databinding.TopRatedMoviesFragmentBinding
import com.example.moviesapp.presentation.view.fragments.BaseFragmet

class TopRatedMoviesFragment :BaseFragmet(){

    private lateinit var binding: TopRatedMoviesFragmentBinding

    override fun initRecyclerView() {
        binding.recyclerView.adapter=adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= TopRatedMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProgress(binding.progressBar)
        moviesViewModel.loadTopMovies()

        initRecyclerView()
        moviesObserver()
    }
}