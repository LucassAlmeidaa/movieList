package com.example.movielist.repository

import com.example.movielist.data.local.model.MovieEntity
import com.example.movielist.model.MovieSearchResult

interface WatchListRepository {
    suspend fun getWatchList(): List<MovieSearchResult>
    suspend fun isMovieInWatchList(movieId: Int): Boolean
    suspend fun addToWatchList(movie: MovieEntity)
    suspend fun removeFromWatchList(movieId: Int)
}
