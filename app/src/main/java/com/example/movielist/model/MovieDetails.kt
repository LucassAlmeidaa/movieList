package com.example.movielist.model

data class MovieDetailsList(
    val poster_path: String?,
    val genres: List<Genre>,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val runtime: Int,
    val backdrop_path: String
)

data class Genre(
    val id: Int,
    val name: String
)