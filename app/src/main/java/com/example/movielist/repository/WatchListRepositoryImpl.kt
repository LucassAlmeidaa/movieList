package com.example.movielist.repository

import com.example.movielist.app.MovieApp
import com.example.movielist.data.local.model.MovieEntity
import com.example.movielist.model.MovieSearchResult

class WatchListRepositoryImpl : WatchListRepository {

    override suspend fun getWatchList(): List<MovieSearchResult> {
        val entities = MovieApp.database.movieDao().getAllMovies()
        return entities.map {
            MovieSearchResult(
                id = it.id,
                title = it.title,
                poster_path = it.posterPath,
                vote_average = 5.0,
                release_date = "2025-05-05"
            )
        }
    }

    override suspend fun isMovieInWatchList(movieId: Int): Boolean {
        val movie = MovieApp.database.movieDao().getMovieById(movieId)
        return movie != null
    }

    override suspend fun addToWatchList(movie: MovieEntity) {
        MovieApp.database.movieDao().insertMovie(movie)
    }

    override suspend fun removeFromWatchList(movieId: Int) {
        MovieApp.database.movieDao().deleteMovieById(movieId)
    }
}
