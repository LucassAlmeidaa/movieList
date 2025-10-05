package com.example.movielist.ui.watchList

import com.example.movielist.model.MovieSearchResult

sealed interface WatchListState {
    data class Success(val result: List<MovieSearchResult>) : WatchListState
    object Loading : WatchListState
    object Empty : WatchListState
    object Error : WatchListState
}