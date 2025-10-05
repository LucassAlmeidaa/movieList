package com.example.movielist.business

import com.example.movielist.model.MovieSearchResult
import com.example.movielist.repository.WatchListRepository
import com.example.movielist.repository.WatchListRepositoryImpl

class WatchListBusinessImp : WatchListBusiness {
    val repository: WatchListRepository = WatchListRepositoryImpl()
    override suspend fun getWatchList(): List<MovieSearchResult> {
        return repository.getWatchList()
    }

}