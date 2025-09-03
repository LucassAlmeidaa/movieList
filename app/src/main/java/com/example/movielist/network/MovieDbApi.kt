package com.example.movielist.network

import com.example.movielist.const.ApiConst.TOKEN
import com.example.movielist.model.MovieList
import retrofit2.http.GET

interface MovieDbApi {
    @GET("movie/now_playing$TOKEN")
    suspend fun getNowPlaying(): MovieList
}