package com.alkemy.challengeandroid.ui.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.challengeandroid.R
import com.alkemy.challengeandroid.data.model.DetailedMovie

class DetailedMovieAdapter(private val movieDetail: DetailedMovie): RecyclerView.Adapter<DetailedMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetailedMovieViewHolder(inflater.inflate(R.layout.poster, parent, false))
    }

    override fun onBindViewHolder(holder: DetailedMovieViewHolder, position: Int) {
       val item = movieDetail
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}