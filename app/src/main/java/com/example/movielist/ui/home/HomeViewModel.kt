package com.example.movielist.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.business.HomeBusiness
import com.example.movielist.business.HomeBusinessImpl
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state
    val business: HomeBusiness = HomeBusinessImpl()
    fun getNowPlaying() {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = business.getNowPlaying()
                _state.value = HomeState.Success(response)
            } catch (e: Exception) {
                _state.value = HomeState.Error
            }
        }
    }

}