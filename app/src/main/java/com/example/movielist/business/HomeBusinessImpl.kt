package com.example.movielist.business

import com.example.movielist.model.MovieApi
import com.example.movielist.repository.HomeRepository
import com.example.movielist.repository.HomeRepositoryImpl

class HomeBusinessImpl : HomeBusiness {

    val repository : HomeRepository = HomeRepositoryImpl()

    override suspend fun getNowPlaying(): List<MovieApi> {
        return repository.getNowPlaying()
    }

    override suspend fun getUpcoming(): List<MovieApi> {
        TODO("Not yet implemented")
    }
}