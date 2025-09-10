package com.example.movielist.ui.details

import com.example.movielist.model.MovieReviewList

sealed interface DetailsReviewState {
    data class Success(val result: List<MovieReviewList>) : DetailsReviewState
    object Loading : DetailsReviewState

    object Empty : DetailsReviewState
    object Error : DetailsReviewState
}