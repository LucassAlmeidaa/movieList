package com.example.movielist.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.business.HomeBusiness
import com.example.movielist.business.HomeBusinessImpl
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _statePopular = MutableLiveData<HomeState>()
    val statePopular: LiveData<HomeState> = _statePopular
    private val _stateList = MutableLiveData<HomeState>()
    val stateList: LiveData<HomeState> = _stateList
    val business: HomeBusiness = HomeBusinessImpl()
    fun getNowPlaying() {
        _stateList.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = business.getNowPlaying()
                _stateList.value = HomeState.Success(response)
            } catch (e: Exception) {
                _stateList.value = HomeState.Error
            }
        }
    }

    fun getUpcoming() {
        _stateList.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = business.getUpcoming()
                _stateList.value = HomeState.Success(response)
            } catch (e: Exception) {
                _stateList.value = HomeState.Error
            }
        }
    }

    fun getPopular() {
        _statePopular.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = business.getPopular()
                _statePopular.value = HomeState.Success(response)
            } catch (e: Exception) {
                _statePopular.value = HomeState.Error
            }
        }
    }

    fun getTopRated() {
        _stateList.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = business.getTopRated()
                _stateList.value = HomeState.Success(response)
            } catch (e: Exception) {
                _stateList.value = HomeState.Error
            }
        }
    }

    fun getPopularList() {
        _stateList.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = business.getPopular()
                _stateList.value = HomeState.Success(response)
            } catch (e: Exception) {
                _stateList.value = HomeState.Error
            }
        }
    }
}