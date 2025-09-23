package com.example.movielist.business

import com.example.movielist.model.SearchResponse
import com.example.movielist.repository.SearchRepository
import com.example.movielist.repository.SearchRepositoryImpl

class SearchBusinessImpl : SearchBusiness {
    val repository: SearchRepository = SearchRepositoryImpl()
    override suspend fun getMoviesByName(searchText: String): SearchResponse {
        return repository.getMoviesByName(searchText)
    }
}