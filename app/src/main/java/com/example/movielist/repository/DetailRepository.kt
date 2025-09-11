package com.example.movielist.repository

import com.example.movielist.model.MovieApi
import com.example.movielist.model.MovieCastList
import com.example.movielist.model.MovieDetailList
import com.example.movielist.model.MovieReviewList

interface DetailRepository {
    suspend fun getMovieInfo(): List<MovieApi>
    suspend fun getMovieReview(movieId: Int): List<MovieReviewList>
    suspend fun getMovieCast(movieId: Int): List<MovieCastList>
    suspend fun getMovieDetail(movieId: Int): List<MovieDetailList>
}