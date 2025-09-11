package com.example.movielist.business

import com.example.movielist.model.MovieCastList
import com.example.movielist.model.MovieDetailsList
import com.example.movielist.model.MovieReviewList
import com.example.movielist.repository.DetailRepository
import com.example.movielist.repository.DetailRepositoryImpl

class DetailsBusinessImpl : DetailsBusiness {
    val repository: DetailRepository = DetailRepositoryImpl()

    override suspend fun getMovieCast(movieId: Int): List<MovieCastList> {
        return repository.getMovieCast(movieId)
    }

    override suspend fun getMovieReview(movieId: Int): List<MovieReviewList> {
        return repository.getMovieReview(movieId)
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetailsList {
        return repository.getMovieDetails(movieId)
    }

}