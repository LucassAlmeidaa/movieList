package com.example.movielist.model

data class MovieApi(
    val id: Int,
    val backdrop_path: String,
    val poster_path: String,
    val title: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val overview: String
)