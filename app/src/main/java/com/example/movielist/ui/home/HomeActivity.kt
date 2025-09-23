package com.example.movielist.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movielist.databinding.ActivityHomeBinding
import com.example.movielist.ui.details.DetailsActivity
import com.example.movielist.ui.home.adapter.HomeAdapter
import com.example.movielist.ui.home.adapter.HomeListener
import com.example.movielist.ui.home.adapter.MovieListAdapter
import com.example.movielist.ui.search.SearchActivity

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
        }
        binding.upcoming.setOnClickListener {
            viewModel.getUpcoming()
        }
        binding.topRated.setOnClickListener {
            viewModel.getTopRated()
        }
        binding.popular.setOnClickListener {
            viewModel.getPopularList()
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