package com.example.moviesapp.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.moviesapp.R
import com.example.moviesapp.presentation.view.adapter.MoviesAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.presentation.Utilis.StateData
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.presentation.view.adapter.ViewPagerAdapter
import com.example.moviesapp.presentation.viewmodel.MoviesViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupViewPager()


    }

    private fun setupTabs() {
        binding.tabs.addTab(binding.tabs.newTab().setText("Popular"))
        binding.tabs.addTab(binding.tabs.newTab().setText("top rated"))
        binding.tabs.addTab(binding.tabs.newTab().setText("favourites"))

        binding.tabs.tabGravity = TabLayout.GRAVITY_FILL
    }

    private fun setupViewPager() {
        setupTabs()

        val adapter = ViewPagerAdapter(supportFragmentManager, binding.tabs.tabCount)
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabs))

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

}