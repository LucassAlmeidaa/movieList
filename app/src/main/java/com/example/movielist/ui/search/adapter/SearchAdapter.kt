package com.example.movielist.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.SearchItemBinding
import com.example.movielist.model.MovieSearchResult
import com.example.movielist.ui.details.adapter.SearchViewHolder

class SearchAdapter(
    private val searchList: List<MovieSearchResult>,
    private val listener: SearchListener
) : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: SearchViewHolder,
        position: Int
    ) {
        holder.bind(searchList[position])
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}