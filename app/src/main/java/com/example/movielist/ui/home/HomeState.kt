package com.example.movielist.ui.home

import com.example.movielist.model.MovieApi


sealed interface HomeState {
    data class Success(val result: List<MovieApi>) : HomeState
    object Loading : HomeState
    object Empty : HomeState
    object Error : HomeState
}