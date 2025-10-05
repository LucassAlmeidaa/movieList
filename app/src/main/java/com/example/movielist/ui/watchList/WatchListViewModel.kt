package com.example.movielist.ui.watchList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.business.WatchListBusiness
import com.example.movielist.business.WatchListBusinessImp
import kotlinx.coroutines.launch

class WatchListViewModel : ViewModel() {
    private val _stateWatchList = MutableLiveData<WatchListState>()
    val stateWatchList: LiveData<WatchListState> = _stateWatchList
    val business: WatchListBusiness = WatchListBusinessImp()

    fun getWatchList() {
        _stateWatchList.value = WatchListState.Loading
        viewModelScope.launch {
            try {
                val response = business.getWatchList()
                if (response.isNotEmpty()) {
                    _stateWatchList.value = WatchListState.Success(response)
                } else {
                    _stateWatchList.value = WatchListState.Empty
                }
            } catch (e: Exception) {
                _stateWatchList.value = WatchListState.Error
            }
        }
    }

}