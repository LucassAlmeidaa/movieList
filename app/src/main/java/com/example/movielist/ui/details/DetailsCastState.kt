package com.example.movielist.ui.details

import com.example.movielist.model.MovieCastList

sealed interface DetailsCastState {
    data class Success(val result: List<MovieCastList>) : DetailsCastState
    object Loading : DetailsCastState
    object Empty : DetailsCastState
    object Error : DetailsCastState
}