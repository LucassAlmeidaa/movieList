package com.example.movielist.ui.details

import android.os.Bundle
import android.util.Log
import android.view.RoundedCorner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.const.ApiConst.POSTER_PATH
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
        viewModel.getMovieDetails(movieId)
        bindObserver()
        bindListeners()
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

        viewModel.stateDetails.observe(this) {
            when (it) {
                DetailsDetailsState.Error -> {
                    Log.v("Teste", "Deu erro")
                }

                is DetailsDetailsState.Success -> {
                    val details = it.results

                    Glide.with(binding.filmPicture)
                        .load(POSTER_PATH + details.poster_path)
                        .fitCenter()
                        .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(30)))
                        .into(binding.filmPicture)

                    Glide.with(binding.moviePoster)
                        .load(POSTER_PATH + details.backdrop_path)
                        .fitCenter()
                        .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(30)))
                        .into(binding.moviePoster)

                    binding.movieTitle.text = details.original_title
                    binding.detailsDescription.text = details.overview
                    binding.detailsDescription.isSelected = true
                }

                DetailsDetailsState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }

                DetailsDetailsState.Empty -> {
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
            binding.detailsReviews.isSelected = true
            binding.detailsDescription.isSelected = false
            binding.detailsCast.isSelected = false
            binding.detailsReviews.isVisible = true
            binding.detailsDescription.isVisible = false
            binding.detailsCast.isVisible = false
        }
        binding.filmCast.setOnClickListener {
            binding.detailsDescription.isVisible = false
            binding.detailsCast.isVisible = true
            binding.detailsReviews.isVisible = false
            binding.detailsReviews.isSelected = false
            binding.detailsDescription.isSelected = false
            binding.detailsCast.isSelected = true
        }
        binding.filmAbout.setOnClickListener {
            binding.detailsDescription.isVisible = true
            binding.detailsCast.isVisible = false
            binding.detailsReviews.isVisible = false
            binding.detailsReviews.isSelected = false
            binding.detailsDescription.isSelected = true
            binding.detailsCast.isSelected = false
        }
    }

}

