package com.example.movielist.business

import com.example.movielist.model.MovieApi
import com.example.movielist.model.MovieCastList
import com.example.movielist.model.MovieReviewList

interface DetailsBusiness {
    suspend fun getMovieDescription(): List<MovieApi>
    suspend fun getMovieCast(movieId: Int): List<MovieCastList>
    suspend fun getMovieReview(movieId: Int): List<MovieReviewList>
}