package com.example.movielist.model

data class SearchResponse(
    val results: List<MovieSearchResult>
)

data class MovieSearchResult(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val vote_average: Double,
    val release_date: String?
)