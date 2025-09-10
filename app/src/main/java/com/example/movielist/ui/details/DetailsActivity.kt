package com.example.movielist.ui.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.movielist.databinding.ActivityDetailsBinding
import com.example.movielist.model.MovieCastList
import com.example.movielist.model.MovieReviewList
import com.example.movielist.ui.details.adapter.DetailsCastAdapter
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
        viewModel.getMovieCast(movieId)
        bindObserver()
    }

    fun bindObserver() {
        viewModel.stateReview.observe(this) {
            when (it) {
                DetailsReviewState.Error -> {
                    Log.v("Teste", "Deu erro")
                }

                is DetailsReviewState.Success -> {
                    bindSuccessStateReviews(it.result)
                }

                DetailsReviewState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }

                DetailsReviewState.Empty -> {
                    Log.v("Teste", "Vaziooooo")
                }
            }
        }
        viewModel.stateCast.observe(this) {
            when (it) {
                DetailsCastState.Error -> {
                    Log.v("Teste", "Deu erro")
                }

                is DetailsCastState.Success -> {
                    bindSuccessStateCast(it.result)
                }

                DetailsCastState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }

                DetailsCastState.Empty -> {
                    Log.v("Teste", "Vaziooooo")
                }
            }
        }
    }

    fun bindSuccessStateReviews(it: List<MovieReviewList>) {
        binding.detailsReviews.adapter = DetailsReviewAdapter(it)
    }

    fun bindSuccessStateCast(it: List<MovieCastList>) {
        binding.detailsCast.adapter = DetailsCastAdapter(it)
    }

    fun bindListeners() {
        binding.filmReviews.setOnClickListener {
            Log.v("TesteClique", "Espera um pouco")
            binding.detailsDescription.isVisible = false
            binding.detailsCast.isVisible = false
            binding.detailsReviews.isVisible = true
        }
        binding.detailsCast.setOnClickListener {
            Log.v("TesteClique", "Espera mais pouco")
            binding.detailsDescription.isVisible = false
            binding.detailsCast.isVisible = true
            binding.detailsReviews.isVisible = false
        }
    }

}

