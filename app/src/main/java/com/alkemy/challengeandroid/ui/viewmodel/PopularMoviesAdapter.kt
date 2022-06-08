package com.alkemy.challengeandroid.ui.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.challengeandroid.R
import com.alkemy.challengeandroid.data.model.PopularMovies
import com.alkemy.challengeandroid.data.network.PopularMovieAPI


class PopularMoviesAdapter(private val moviePoster:List<PopularMovies>):
    RecyclerView.Adapter<PopularMoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
       val inflater = LayoutInflater.from(parent.context)
       return PopularMoviesViewHolder(inflater.inflate(R.layout.poster, parent, false))
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val item = moviePoster[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = moviePoster.size

}