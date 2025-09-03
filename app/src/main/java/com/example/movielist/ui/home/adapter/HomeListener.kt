package com.example.movielist.ui.home.adapter

import com.example.movielist.model.MovieApi

interface HomeListener {
    fun onClickItem(movieId: Int)
}