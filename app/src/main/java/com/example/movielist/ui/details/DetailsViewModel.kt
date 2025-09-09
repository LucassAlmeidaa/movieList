package com.example.movielist.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.business.DetailsBusiness
import com.example.movielist.business.DetailsBusinessImpl
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val _stateReview = MutableLiveData<DetailsState>()
    val stateReview: LiveData<DetailsState> = _stateReview
    val business: DetailsBusiness = DetailsBusinessImpl()

    fun getMovieReview(movieId: Int){
        _stateReview.value = DetailsState.Loading
        viewModelScope.launch {
            try {
                val response = business.getMovieReview(movieId)
                Log.v("testeApi", response.toString())
                _stateReview.value = DetailsState.Success(response)
            } catch (e: Exception) {
                _stateReview.value = DetailsState.Error
            }
        }
    }
}

