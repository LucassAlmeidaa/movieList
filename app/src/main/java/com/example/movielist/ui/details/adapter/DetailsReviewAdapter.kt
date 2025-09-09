package com.example.movielist.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.DetailsReviewsItemBinding
import com.example.movielist.model.MovieReviewList

class DetailsReviewAdapter(
    private val listReview: List<MovieReviewList>
) : RecyclerView.Adapter<DetailsReviewViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsReviewViewHolder {
        val binding = DetailsReviewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsReviewViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DetailsReviewViewHolder,
        position: Int
    ) {
        holder.bind(listReview[position])
    }

    override fun getItemCount(): Int {
        return listReview.size
    }
}