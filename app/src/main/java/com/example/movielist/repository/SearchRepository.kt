package com.example.movielist.repository

import com.example.movielist.model.SearchResponse

interface SearchRepository {
    suspend fun getMoviesByName(searchText: String): SearchResponse
}