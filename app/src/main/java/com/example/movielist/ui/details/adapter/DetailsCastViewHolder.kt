package com.example.movielist.ui.details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.const.ApiConst.POSTER_PATH
import com.example.movielist.databinding.DetailCastItemBinding
import com.example.movielist.model.MovieCastList

class DetailsCastViewHolder (
    private val binding: DetailCastItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieCastList) {
        bindView(item)
    }

    private fun bindView(item: MovieCastList) {
        Glide.with(binding.castAvatar)
            .load(POSTER_PATH + item.profile_path)
            .fitCenter()
            .into(binding.castAvatar)
        binding.castName.text = item.name
    }
}