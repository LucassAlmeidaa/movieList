package com.example.movielist.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.MovieListItemBinding
import com.example.movielist.model.MovieApi

class HomeAdapter(
    private val items: List<MovieApi>,
    private val listener: HomeListener
) :
    RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeViewHolder {
        val binding =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: HomeViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}