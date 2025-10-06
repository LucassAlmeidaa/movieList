package com.example.movielist.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.movielist.R
import com.example.movielist.databinding.ActivitySearchBinding
import com.example.movielist.ui.details.DetailsActivity
import com.example.movielist.ui.home.HomeActivity
import com.example.movielist.ui.search.adapter.SearchAdapter
import com.example.movielist.ui.search.adapter.SearchListener
import com.example.movielist.ui.watchList.WatchListActivity

class SearchActivity : AppCompatActivity(), SearchListener {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel = SearchViewModel()
    private var searchText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchText = intent.getStringExtra("QUERY").toString()
        viewModel.getMoviesByName(searchText)
        bindObserver()
        bindListerners()
        setupNavigation()
    }

    fun bindObserver() {
        viewModel.stateSearch.observe(this) {
            when (it) {
                SearchState.Error -> {
                    Log.v("Teste", "Deu erro")
                }

                is SearchState.Success -> {
                    binding.searchList.adapter = SearchAdapter(it.result.results, this)
                }

                SearchState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }

                SearchState.Empty -> {
                    binding.notFind.isVisible = true
                    binding.searchList.isVisible = false
                }
            }
        }
    }

    override fun onClickItem(movieId: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }

    fun bindListerners() {
        binding.searchButton.setOnClickListener {
            val query = binding.searchSearch.text.toString()
            if (query.isNotEmpty()) {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("QUERY", query)
                startActivity(intent)
            }
        }
    }

    private fun setupNavigation() {
        val navView = binding.navView
        navView.selectedItemId = R.id.navigation_search
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