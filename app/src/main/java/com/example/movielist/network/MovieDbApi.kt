package com.example.movielist.network

import com.example.movielist.const.ApiConst.TOKEN
import com.example.movielist.model.MovieList
import retrofit2.http.GET

interface MovieDbApi {
    @GET("movie/popular$TOKEN")
    suspend fun getPopular(): MovieList

    @GET("movie/now_playing$TOKEN")
    suspend fun getNowPlaying(): MovieList

    @GET("movie/upcoming$TOKEN")
    suspend fun getUpcoming(): MovieList

    @GET("movie/top_rated$TOKEN")
    suspend fun getTopRated(): MovieList
}