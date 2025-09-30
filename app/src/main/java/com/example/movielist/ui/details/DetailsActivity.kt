package com.example.movielist.ui.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.R
import com.example.movielist.const.ApiConst.POSTER_PATH
import com.example.movielist.databinding.ActivityDetailsBinding
import com.example.movielist.model.MovieCastList
import com.example.movielist.model.MovieReviewList
import com.example.movielist.ui.details.adapter.DetailsCastAdapter
import com.example.movielist.ui.details.adapter.DetailsReviewAdapter
import com.example.movielist.ui.home.HomeActivity
import com.example.movielist.ui.search.SearchActivity

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

        val navView = binding.navView
        navView.selectedItemId = R.id.navigation_search
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }

                R.id.navigation_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    fun bindObserver() {
        viewModel.stateReview.observe(this) {
            when (it) {
                DetailsReviewState.Error -> {
                    Log.v("Teste", "Deu erro")
                }

                is DetailsReviewState.Success -> {
                    Log.v("danilo", "caiu aqui")
                    bindSuccessStateReviews(it.result)
                }

                DetailsReviewState.Loading -> {
                    Log.v("Teste", "Espera um pouco")
                }

                DetailsReviewState.Empty -> {
                    Log.v("teste", "caiuuuu")
                    binding.notFindReview.isVisible = true
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
                    val realiseYear = details.release_date

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
                    binding.releaseDate.text = realiseYear.substring(0, 4)
                    binding.minutes.text = "${details.runtime} min"
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
        binding.detailsReviews.isVisible = true
    }

    fun bindSuccessStateCast(it: List<MovieCastList>) {
        binding.detailsCast.adapter = DetailsCastAdapter(it)
    }

    fun setDefaultViewOnClick() = with(binding) {
        detailsReviews.isSelected = false
        detailsDescription.isSelected = false
        detailsCast.isSelected = false
        detailsDescription.isVisible = false
        detailsCast.isVisible = false
        reviewScreen.isVisible = false
    }

    fun bindListeners() {
        binding.filmReviews.setOnClickListener {
            setDefaultViewOnClick()
            binding.detailsReviews.isSelected = true
            binding.reviewScreen.isVisible = true
        }
        binding.filmCast.setOnClickListener {
            setDefaultViewOnClick()
            binding.detailsCast.isVisible = true
            binding.detailsCast.isSelected = true
        }
        binding.filmAbout.setOnClickListener {
            setDefaultViewOnClick()
            binding.detailsDescription.isVisible = true
            binding.detailsDescription.isSelected = true
        }
    }

}

