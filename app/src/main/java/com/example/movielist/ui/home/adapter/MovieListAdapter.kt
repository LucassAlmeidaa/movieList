package com.example.movielist.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.MovieListNowPlayingListBinding
import com.example.movielist.model.MovieApi

class MovieListAdapter(
    private val items: List<MovieApi>,
    private val listener: HomeListener
) :
    RecyclerView.Adapter<HomeViewHolderUpcoming>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeViewHolderUpcoming {
        val binding =
            MovieListNowPlayingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolderUpcoming(binding, listener)
    }

    override fun onBindViewHolder(
        holder: HomeViewHolderUpcoming,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}