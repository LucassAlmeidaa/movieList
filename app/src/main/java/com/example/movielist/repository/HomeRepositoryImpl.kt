package com.example.movielist.repository

import com.example.movielist.model.MovieApi
import com.example.movielist.network.RetrofitInstance

class HomeRepositoryImpl : HomeRepository {

    val api = RetrofitInstance.movieApi

    override suspend fun getPopular(): List<MovieApi> {
        return api.getPopular().results
    }

    override suspend fun getNowPlaying(): List<MovieApi> {
        return api.getNowPlaying().results
    }

    override suspend fun getUpcoming(): List<MovieApi> {
        return api.getUpcoming().results
    }
}