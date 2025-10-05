package com.example.movielist.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.app.MovieApp
import com.example.movielist.business.DetailsBusiness
import com.example.movielist.business.DetailsBusinessImpl
import com.example.movielist.data.local.model.MovieEntity
import com.example.movielist.repository.WatchListRepositoryImpl
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val _stateReview = MutableLiveData<DetailsReviewState>()
    val stateReview: LiveData<DetailsReviewState> = _stateReview
    private val _stateCast = MutableLiveData<DetailsCastState>()
    val stateCast: LiveData<DetailsCastState> = _stateCast

    private val _stateDetails = MutableLiveData<DetailsDetailsState>()
    val stateDetails: LiveData<DetailsDetailsState> = _stateDetails

    private val _isInWatchList = MutableLiveData<Boolean>()
    val isInWatchList: LiveData<Boolean> get() = _isInWatchList

    private val business: DetailsBusiness = DetailsBusinessImpl()
    private val repository = WatchListRepositoryImpl()

    // 🔹 Busca os reviews
    fun getMovieReview(movieId: Int) {
        _stateReview.value = DetailsReviewState.Loading
        viewModelScope.launch {
            try {
                val response = business.getMovieReview(movieId)
                if (response.isNotEmpty()) {
                    _stateReview.value = DetailsReviewState.Success(response)
                } else {
                    _stateReview.value = DetailsReviewState.Empty
                }
            } catch (e: Exception) {
                _stateReview.value = DetailsReviewState.Error
            }
        }
    }

    // 🔹 Busca o elenco
    fun getMovieCast(movieId: Int) {
        _stateCast.value = DetailsCastState.Loading
        viewModelScope.launch {
            try {
                val response = business.getMovieCast(movieId)
                if (response.isNotEmpty()) {
                    _stateCast.value = DetailsCastState.Success(response)
                } else {
                    _stateCast.value = DetailsCastState.Empty
                }
            } catch (e: Exception) {
                _stateCast.value = DetailsCastState.Error
            }
        }
    }

    // 🔹 Busca os detalhes
    fun getMovieDetails(movieId: Int) {
        _stateDetails.value = DetailsDetailsState.Loading
        viewModelScope.launch {
            try {
                val response = business.getMovieDetail(movieId)
                _stateDetails.value = DetailsDetailsState.Success(response)
            } catch (e: Exception) {
                _stateDetails.value = DetailsDetailsState.Error
            }
        }
    }

    // 🔹 Verifica se o filme está na watchlist
    fun checkIfMovieIsInWatchList(movieId: Int) {
        viewModelScope.launch {
            val isInList = repository.isMovieInWatchList(movieId)
            _isInWatchList.postValue(isInList)
        }
    }

    // 🔹 Adiciona ou remove da lista
    fun toggleWatchList(movie: MovieEntity) {
        viewModelScope.launch {
            val isInList = repository.isMovieInWatchList(movie.id)
            if (isInList) {
                repository.removeFromWatchList(movie.id)
                _isInWatchList.postValue(false)
            } else {
                repository.addToWatchList(movie)
                _isInWatchList.postValue(true)
            }
        }
    }
}
