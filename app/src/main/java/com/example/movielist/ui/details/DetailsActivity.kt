package com.example.movielist.ui.details

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.R
import com.example.movielist.const.ApiConst.POSTER_PATH
import com.example.movielist.data.local.model.MovieEntity
import com.example.movielist.databinding.ActivityDetailsBinding
import com.example.movielist.model.MovieCastList
import com.example.movielist.model.MovieReviewList
import com.example.movielist.ui.details.adapter.DetailsCastAdapter
import com.example.movielist.ui.details.adapter.DetailsReviewAdapter
import com.example.movielist.ui.home.HomeActivity
import com.example.movielist.ui.search.SearchActivity
import com.example.movielist.ui.watchList.WatchListActivity

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel = DetailsViewModel()
    private var movieId: Int = 0
    private var currentMovie: MovieEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra("movieId", 0)
        viewModel.getMovieReview(movieId)
        viewModel.getMovieCast(movieId)
        viewModel.getMovieDetails(movieId)
        viewModel.checkIfMovieIsInWatchList(movieId)
        bindObserver()
        bindListeners()
        setupNavigation()
    }

    private fun bindObserver() {
        viewModel.stateReview.observe(this) {
            when (it) {
                is DetailsReviewState.Success -> bindSuccessStateReviews(it.result)
                DetailsReviewState.Empty -> binding.notFindReview.isVisible = true
                else -> {}
            }
        }

        viewModel.stateCast.observe(this) {
            if (it is DetailsCastState.Success) bindSuccessStateCast(it.result)
        }

        viewModel.stateDetails.observe(this) {
            if (it is DetailsDetailsState.Success) {
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
                binding.releaseDate.text = details.release_date.substring(0, 4)
                binding.minutes.text = "${details.runtime} min"

                currentMovie = MovieEntity(
                    id = details.id,
                    title = details.original_title,
                    posterPath = details.poster_path ?: details.backdrop_path
                )
            }
        }

        viewModel.isInWatchList.observe(this) { isInList ->
            val icon = if (isInList)
                R.drawable.ic_star_filled
            else
                R.drawable.ic_star_outline
            binding.addToWatchList.setImageResource(icon)
        }
    }

    private fun bindSuccessStateReviews(it: List<MovieReviewList>) {
        binding.detailsReviews.adapter = DetailsReviewAdapter(it)
        binding.detailsReviews.isVisible = true
    }

    private fun bindSuccessStateCast(it: List<MovieCastList>) {
        binding.detailsCast.adapter = DetailsCastAdapter(it)
    }

    private fun setDefaultViewOnClick() = with(binding) {
        detailsReviews.isSelected = false
        detailsDescription.isSelected = false
        detailsCast.isSelected = false
        detailsDescription.isVisible = false
        detailsCast.isVisible = false
        reviewScreen.isVisible = false
    }

    private fun bindListeners() {
        binding.filmReviews.setOnClickListener {
            setDefaultViewOnClick()
            binding.reviewScreen.isVisible = true
        }

        binding.filmCast.setOnClickListener {
            setDefaultViewOnClick()
            binding.detailsCast.isVisible = true
        }

        binding.filmAbout.setOnClickListener {
            setDefaultViewOnClick()
            binding.detailsDescription.isVisible = true
        }

        binding.addToWatchList.setOnClickListener {
            currentMovie?.let { movie ->
                viewModel.toggleWatchList(movie)
            } ?: run {
                Toast.makeText(this, "Filme ainda não carregado!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupNavigation() {
        val navView = binding.navView
        navView.selectedItemId = R.id.navigation_search
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java)); true
                }

                R.id.navigation_search -> {
                    startActivity(Intent(this, SearchActivity::class.java)); true
                }

                R.id.navigation_watchlist -> {
                    startActivity(Intent(this, WatchListActivity::class.java)); true
                }

                else -> false
            }
        }
    }
}
