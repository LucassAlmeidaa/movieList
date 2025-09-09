package com.example.movielist.model

data class MovieReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<MovieReviewList>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieReviewList(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val id: String,
    val url: String
)

data class AuthorDetails(
    val name: String?,
    val username: String?,
    val avatar_path: String?,
    val rating: Double?
)