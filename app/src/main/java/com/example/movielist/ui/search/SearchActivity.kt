package com.example.movielist.ui.search

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movielist.databinding.ActivitySearchBinding
import com.example.movielist.ui.search.adapter.SearchListener
import com.example.movielist.ui.search.adapter.SearchViewModel

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
    }

    fun bindObserver() {
        viewModel.stateSearch.observe(this) {
            when (it) {
                SearchState.Error -> {
                    Log.v("Teste", "Deu erro")
                }

                is SearchState.Success -> {
                    Log.v("Teste", "Deu erro")
                }

                SearchState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }

                SearchState.Empty -> {
                    Log.v("Teste", "Vaziooooo")
                }
            }
        }
    }

    override fun onClickItem(movieId: Int) {
        TODO("Not yet implemented")
    }
}