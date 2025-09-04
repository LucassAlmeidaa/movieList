package com.example.movielist.repository

import com.example.movielist.model.MovieApi

interface HomeRepository {
    suspend fun getNowPlaying() : List<MovieApi>
    suspend fun getUpcoming() : List<MovieApi>

    suspend fun getPopular(): List<MovieApi>
}