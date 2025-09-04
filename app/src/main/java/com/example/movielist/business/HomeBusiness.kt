package com.example.movielist.business

import com.example.movielist.model.MovieApi
import com.example.movielist.model.MovieList

interface HomeBusiness {
    suspend fun getNowPlaying() : List<MovieApi>
    suspend fun getUpcoming() : List<MovieApi>

    suspend fun getPopular(): List<MovieApi>
}