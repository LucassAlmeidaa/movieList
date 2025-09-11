package com.example.movielist.business

import com.example.movielist.model.MovieCastList
import com.example.movielist.model.MovieDetailsList
import com.example.movielist.model.MovieReviewList

interface DetailsBusiness {
    suspend fun getMovieCast(movieId: Int): List<MovieCastList>
    suspend fun getMovieReview(movieId: Int): List<MovieReviewList>
    suspend fun getMovieDetail(movieId: Int): MovieDetailsList
}