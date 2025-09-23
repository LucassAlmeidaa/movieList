package com.example.movielist.business

import com.example.movielist.model.SearchResponse

interface SearchBusiness {
    suspend fun getMoviesByName(searchText: String): SearchResponse
}