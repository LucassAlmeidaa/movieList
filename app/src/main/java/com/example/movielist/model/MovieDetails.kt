package com.example.movielist.model

data class MovieDetailsList(
    val id: Int,
    val poster_path: String?,
    val genres: List<Genre>,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val runtime: Int,
    val backdrop_path: String,
    val is_on_watchlist: String
)

data class Genre(
    val id: Int,
    val name: String
)