package com.example.movielist.model

data class MovieCastResponse(
    val results: List<MovieCastList>,
)

data class MovieCastList(
    val id: Int,
    val name: String,
    val profile_path: String
)