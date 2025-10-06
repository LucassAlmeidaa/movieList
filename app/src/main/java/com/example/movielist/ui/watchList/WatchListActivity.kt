package com.example.movielist.ui.watchList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movielist.R
import com.example.movielist.data.local.dao.MovieDao
import com.example.movielist.databinding.ActivityWatchlistBinding
import com.example.movielist.ui.details.DetailsActivity
import com.example.movielist.ui.home.HomeActivity
import com.example.movielist.ui.search.SearchActivity
import com.example.movielist.ui.watchList.adapter.WatchListListener
import com.example.movielist.ui.watchList.adapter.WatchlistAdapter

class WatchListActivity : AppCompatActivity(), WatchListListener {
    private lateinit var binding: ActivityWatchlistBinding
    private val viewModel = WatchListViewModel()
    private lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getWatchList()
        bindObserver()
        setupNavigation()
    }

    fun bindObserver() {
        viewModel.stateWatchList.observe(this) {
            when (it) {
                WatchListState.Error -> {
                    Log.v("Teste", "Deu erro")
                }

                is WatchListState.Success -> {
                    binding.rvWatchlist.adapter = WatchlistAdapter(it.result, this)
                }

                WatchListState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }

                WatchListState.Empty -> {
                    Log.v("Teste", "vazio")
                }
            }
        }
    }

    override fun onClickItem(movieId: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }

    private fun setupNavigation() {
        val navView = binding.navView
        navView.selectedItemId = R.id.navigation_watchlist
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java)); true
                }

                R.id.navigation_search -> {
                    startActivity(Intent(this, SearchActivity::class.java)); true
                }

                R.id.navigation_watchlist -> {
                    startActivity(Intent(this, WatchListActivity::class.java)); true
                }

                else -> false
            }
        }
    }
}