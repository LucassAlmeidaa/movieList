package com.example.movielist.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movielist.R
import com.example.movielist.databinding.ActivityHomeBinding
import com.example.movielist.ui.details.DetailsActivity
import com.example.movielist.ui.home.adapter.HomeAdapter
import com.example.movielist.ui.home.adapter.HomeListener
import com.example.movielist.ui.home.adapter.MovieListAdapter
import com.example.movielist.ui.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), HomeListener {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindObserver()
        bindListeners()
        viewModel.getPopular()
        viewModel.getNowPlaying()
        val navView = binding.navView

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    fun bindObserver() {
        viewModel.statePopular.observe(this){
            when (it) {
                HomeState.Error -> {
                    Log.v("Teste", "Deu erro")
                }
                is HomeState.Success -> {
                    binding.movieList.adapter = HomeAdapter(it.result, this)
                }
                HomeState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }
                HomeState.Empty -> {
                    Log.v("Teste", "Vaziooooo")
                }
            }
        }

        viewModel.stateList.observe(this){
            when (it) {
                HomeState.Error -> {
                    Log.v("Teste", "Deu erro")
                }
                is HomeState.Success -> {
                    binding.moviesNowPlaying.adapter = MovieListAdapter(it.result, this)
                }
                HomeState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }
                HomeState.Empty -> {
                    Log.v("Teste", "Vaziooooo")
                }
            }
        }

    }

    fun bindListeners() {
        binding.nowPlayingBtn.setOnClickListener {
            viewModel.getNowPlaying()
            binding.nowPlayingBtn.isSelected = true
            binding.popular.isSelected = false
            binding.topRated.isSelected = false
            binding.upcoming.isSelected = false
        }
        binding.upcoming.setOnClickListener {
            viewModel.getUpcoming()
            binding.nowPlayingBtn.isSelected = false
            binding.popular.isSelected = false
            binding.topRated.isSelected = false
            binding.upcoming.isSelected = true
        }
        binding.topRated.setOnClickListener {
            viewModel.getTopRated()
            binding.nowPlayingBtn.isSelected = false
            binding.popular.isSelected = false
            binding.topRated.isSelected = true
            binding.upcoming.isSelected = false
        }
        binding.popular.setOnClickListener {
            viewModel.getPopularList()
            binding.nowPlayingBtn.isSelected = false
            binding.popular.isSelected = true
            binding.topRated.isSelected = false
            binding.upcoming.isSelected = false
        }
        binding.searchButton.setOnClickListener {
            val query = binding.homeSearch.text.toString()
            if (query.isNotEmpty()) {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("QUERY", query)
                startActivity(intent)
            }
        }
    }


    override fun onClickItem(movieId: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }

}