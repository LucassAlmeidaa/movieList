package com.example.movielist.ui.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.movielist.databinding.ActivityDetailsBinding
import com.example.movielist.model.MovieReviewList
import com.example.movielist.ui.details.adapter.DetailsReviewAdapter

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val viewModel = DetailsViewModel()
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra("movieId", 0)
        viewModel.getMovieReview(movieId)
        bindObserver()
    }

    fun bindObserver() {
        viewModel.stateReview.observe(this) {
            when (it) {
                DetailsState.Error -> {
                    Log.v("Teste", "Deu erro")
                }
                is DetailsState.Success -> {
                    bindSuccessState(it.result)
                }
                DetailsState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }
                DetailsState.Empty -> {
                    Log.v("Teste", "Vaziooooo")
                }
            }
        }
    }

    fun bindSuccessState(it: List<MovieReviewList>) {
        binding.detailsDescription.isVisible = false
        binding.detailsCast.isVisible = false
        binding.detailsReviews.isVisible = true
        binding.detailsReviews.adapter = DetailsReviewAdapter(it)
    }

}

