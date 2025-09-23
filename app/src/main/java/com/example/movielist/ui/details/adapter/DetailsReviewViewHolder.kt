package com.example.movielist.ui.details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.const.ApiConst.POSTER_PATH
import com.example.movielist.databinding.DetailsReviewsItemBinding
import com.example.movielist.model.MovieReviewList

class DetailsReviewViewHolder(
    private val binding: DetailsReviewsItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieReviewList) {
        bindView(item)
    }

    private fun bindView(item: MovieReviewList) {
        Glide.with(binding.avatarReview)
            .load(POSTER_PATH + item.author_details.avatar_path)
            .fitCenter()
            .into(binding.avatarReview)
        binding.nicknameReview.text = item.author
        binding.contentReview.text = item.content
    }
}