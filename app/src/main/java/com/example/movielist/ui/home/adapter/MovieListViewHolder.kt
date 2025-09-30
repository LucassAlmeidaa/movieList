package com.example.movielist.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.const.ApiConst.POSTER_PATH
import com.example.movielist.databinding.MovieListNowPlayingListBinding
import com.example.movielist.model.MovieApi

class MovieListViewHolder(
    private val binding: MovieListNowPlayingListBinding,
    private val listener: HomeListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieApi) {
        bindView(item)
        bindClick(item.id)
    }

    private fun bindView(item: MovieApi) {
        Glide.with(binding.view)
            .load(POSTER_PATH + item.poster_path)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(30)))
            .fitCenter()
            .into(binding.view)
    }

    private fun bindClick(movieId: Int) {
        binding.root.setOnClickListener {
            listener.onClickItem(movieId)
        }
    }
}