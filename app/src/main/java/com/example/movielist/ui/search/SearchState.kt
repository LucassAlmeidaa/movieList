package com.example.movielist.ui.search

import com.example.movielist.model.SearchResponse

sealed interface SearchState {
    data class Success(val result: SearchResponse) : SearchState
    object Loading : SearchState
    object Empty : SearchState
    object Error : SearchState
}