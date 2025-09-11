package com.example.movielist.model

data class MovieCastResponse(
    val cast: List<MovieCastList>,
)

data class MovieCastList(
    val id: Int,
    val name: String,
    val profile_path: String
)