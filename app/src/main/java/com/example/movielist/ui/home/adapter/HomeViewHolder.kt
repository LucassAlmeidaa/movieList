package com.example.movielist.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.const.ApiConst.POSTER_PATH
import com.example.movielist.databinding.MovieListItemBinding
import com.example.movielist.model.MovieApi

class HomeViewHolder(
    private val binding: MovieListItemBinding,
    private val listener: HomeListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieApi) {
        bindView(item)
        bindClick(item.id)
    }

    private fun bindView(item: MovieApi) {
        Glide.with(binding.view)
            .load(POSTER_PATH + item.poster_path)
            .fitCenter()
            .into(binding.view)

        binding.numberItem.text = position.toString()
    }

    private fun bindClick(movieId: Int) {
        binding.root.setOnClickListener {
            listener.onClickItem(movieId)
        }
    }
}