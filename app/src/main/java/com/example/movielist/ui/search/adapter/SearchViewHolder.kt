package com.example.movielist.ui.details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.const.ApiConst.POSTER_PATH
import com.example.movielist.databinding.SearchItemBinding
import com.example.movielist.model.MovieSearchResult
import com.example.movielist.ui.search.adapter.SearchListener

class SearchViewHolder(
    private val binding: SearchItemBinding,
    private val listener: SearchListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieSearchResult) {
        bindView(item)
        onClickItem(item.id)
    }

    private fun bindView(item: MovieSearchResult) {
        Glide.with(binding.searchItemImage)
            .load(POSTER_PATH + item.poster_path)
            .fitCenter()
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(30)))
            .into(binding.searchItemImage)
        binding.searchItemTitle.text = item.title
    }

    private fun onClickItem(movieId: Int) {
        binding.root.setOnClickListener {
            listener.onClickItem(movieId)
        }
    }
}