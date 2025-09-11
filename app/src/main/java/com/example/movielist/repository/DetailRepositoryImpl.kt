package com.example.movielist.repository

import com.example.movielist.model.MovieApi
import com.example.movielist.model.MovieCastList
import com.example.movielist.model.MovieDetailList
import com.example.movielist.model.MovieReviewList
import com.example.movielist.network.RetrofitInstance

class DetailRepositoryImpl : DetailRepository {
    val api = RetrofitInstance.movieApi
    override suspend fun getMovieCast(movieId: Int): List<MovieCastList> {
        return api.getMovieCast(movieId).cast
    }

    override suspend fun getMovieDetail(movieId: Int): List<MovieDetailList> {
        return api.getMovieDetails(movieId)
    }

    override suspend fun getMovieInfo(): List<MovieApi> {
        TODO("Not yet implemented")
    }


    override suspend fun getMovieReview(movieId: Int): List<MovieReviewList> {
        return api.getMovieReviews(movieId).results
    }
}