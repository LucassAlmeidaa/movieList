package com.example.movielist.network

import com.example.movielist.const.ApiConst.URL_BASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val movieApi = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieDbApi::class.java)
}