package com.example.movielist.model

data class MovieDetailList(
    val poster_path: String?,
    val genres: List<Genre>,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val runtime: Int
) : List<MovieDetailList>

data class Genre(
    val id: Int,
    val name: String
)