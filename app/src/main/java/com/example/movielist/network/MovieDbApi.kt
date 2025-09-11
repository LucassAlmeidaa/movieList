package com.example.movielist.network

import com.example.movielist.const.ApiConst.TOKEN
import com.example.movielist.model.MovieCastResponse
import com.example.movielist.model.MovieDetailsList
import com.example.movielist.model.MovieList
import com.example.movielist.model.MovieReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbApi {
    @GET("movie/popular$TOKEN")
    suspend fun getPopular(): MovieList

    @GET("movie/now_playing$TOKEN")
    suspend fun getNowPlaying(): MovieList

    @GET("movie/upcoming$TOKEN")
    suspend fun getUpcoming(): MovieList

    @GET("movie/top_rated$TOKEN")
    suspend fun getTopRated(): MovieList

    @GET("movie/{movie_id}/reviews$TOKEN")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int
    ): MovieReviewResponse

    @GET("movie/{movie_id}/credits$TOKEN")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Int
    ): MovieCastResponse

    @GET("movie/{movie_id}$TOKEN")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsList
}