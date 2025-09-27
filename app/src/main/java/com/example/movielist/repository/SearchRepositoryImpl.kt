package com.example.movielist.repository

import android.util.Log
import com.example.movielist.model.SearchResponse
import com.example.movielist.network.RetrofitInstance

class SearchRepositoryImpl : SearchRepository {
    val api = RetrofitInstance.movieApi
    override suspend fun getMoviesByName(searchText: String): SearchResponse {
        return api.searchMovies(searchText)
    }
}