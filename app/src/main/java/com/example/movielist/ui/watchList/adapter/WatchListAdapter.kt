package com.example.movielist.ui.watchList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.ItemWatchlistBinding
import com.example.movielist.model.MovieSearchResult

class WatchlistAdapter(
    private val movies: List<MovieSearchResult>,
    private val listener: WatchListListener
) : RecyclerView.Adapter<WatchListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WatchListViewHolder {
        val binding =
            ItemWatchlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchListViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}
