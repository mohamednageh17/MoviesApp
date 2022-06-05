package com.example.moviesapp.presentation.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviesapp.R
import com.example.moviesapp.presentation.view.fragments.FavouriteMoviesFragment
import com.example.moviesapp.presentation.view.fragments.PopularMoviesFragment
import com.example.moviesapp.presentation.view.fragments.TopRatedMoviesFragment

class ViewPagerAdapter(fm: FragmentManager, var totalTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PopularMoviesFragment()
            1 -> TopRatedMoviesFragment()
            2 -> FavouriteMoviesFragment()
            else -> getItem(position)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> R.string.Popular_Movies.toString().capitalize()
            1 -> R.string.Top_Rated_Movies.toString().capitalize()
            2 -> R.string.Favourite_Movies.toString().capitalize()
            else -> ""
        }
    }

    override fun getCount() = totalTabs
}