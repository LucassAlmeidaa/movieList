package com.example.movielist.business

import com.example.movielist.model.MovieSearchResult
import com.example.movielist.model.SearchResponse

interface WatchListBusiness {
    suspend fun getWatchList(): List<MovieSearchResult>
}