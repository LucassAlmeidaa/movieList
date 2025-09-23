package com.example.movielist.ui.search.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.business.SearchBusiness
import com.example.movielist.business.SearchBusinessImpl
import com.example.movielist.ui.search.SearchState
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _stateSearch = MutableLiveData<SearchState>()
    val stateSearch: LiveData<SearchState> = _stateSearch
    val business: SearchBusiness = SearchBusinessImpl()

    fun getMoviesByName(searchText: String) {
        _stateSearch.value = SearchState.Loading
        viewModelScope.launch {
            try {
                val response = business.getMoviesByName(searchText)
                if (response.results.isNotEmpty()) {
                    _stateSearch.value = SearchState.Success(response)
                } else {
                    _stateSearch.value = SearchState.Empty
                }
                _stateSearch.value = SearchState.Success(response)
            } catch (e: Exception) {
                _stateSearch.value = SearchState.Error
            }
        }
    }
}