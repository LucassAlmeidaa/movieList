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
    private val _stateReview = MutableLiveData<DetailsReviewState>()
    private val _stateCast = MutableLiveData<DetailsCastState>()
    val stateReview: LiveData<DetailsReviewState> = _stateReview
    val stateCast: LiveData<DetailsCastState> = _stateCast
    val business: DetailsBusiness = DetailsBusinessImpl()

    fun getMovieReview(movieId: Int) {
        _stateReview.value = DetailsReviewState.Loading
        viewModelScope.launch {
            try {
                val response = business.getMovieReview(movieId)
                Log.v("testeApi", response.toString())
                _stateReview.value = DetailsReviewState.Success(response)
            } catch (e: Exception) {
                _stateReview.value = DetailsReviewState.Error
            }
        }
    }

    fun getMovieCast(movieId: Int) {
        _stateCast.value = DetailsCastState.Loading
        viewModelScope.launch {
            try {
                val response = business.getMovieCast(movieId)
                Log.v("testeApi", response.toString())
                _stateCast.value = DetailsCastState.Success(response)
            } catch (e: Exception) {
                _stateCast.value = DetailsCastState.Error
            }
        }
    }
}

