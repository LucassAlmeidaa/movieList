package com.example.movielist.repository

import com.example.movielist.model.MovieApi
import com.example.movielist.network.RetrofitInstance

class HomeRepositoryImpl : HomeRepository {

    val api = RetrofitInstance.movieApi

    override suspend fun getNowPlaying(): List<MovieApi> {
        return api.getNowPlaying().results
    }

    override suspend fun getUpcoming(): List<MovieApi> {
        TODO("Not yet implemented")
    }
}