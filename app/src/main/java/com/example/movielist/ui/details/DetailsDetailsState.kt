package com.example.movielist.ui.details

import com.example.movielist.model.MovieDetailsList

sealed interface DetailsDetailsState {
    data class Success(val results: MovieDetailsList) : DetailsDetailsState
    object Loading : DetailsDetailsState
    object Empty : DetailsDetailsState
    object Error : DetailsDetailsState
}