package com.example.movielist.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.DetailCastItemBinding
import com.example.movielist.model.MovieCastList

class DetailsCastAdapter(
    private val listCast: List<MovieCastList>
) : RecyclerView.Adapter<DetailsCastViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsCastViewHolder {
        val binding =
            DetailCastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsCastViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DetailsCastViewHolder,
        position: Int
    ) {
        holder.bind(listCast[position])
    }

    override fun getItemCount(): Int {
        return listCast.size
    }
}