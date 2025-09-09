package com.example.movielist.ui.details

import com.example.movielist.model.MovieReviewList

sealed interface DetailsState {
    data class Success(val result: List<MovieReviewList>) : DetailsState
    object Loading : DetailsState

    object Empty : DetailsState
    object Error : DetailsState
}