package com.alkemy.challengeandroid.ui.viewmodel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.challengeandroid.data.model.DetailedMovie
import com.alkemy.challengeandroid.data.model.PopularMovies
import com.alkemy.challengeandroid.databinding.DetailBinding

import com.squareup.picasso.Picasso


class DetailedMovieViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val binding = DetailBinding.bind(view)

    fun bind(item: DetailedMovie){
        val url = "https://image.tmdb.org/t/p/original".plus(item.posterPath)
        Picasso.get().load(url).into(binding.ivMovie)
        binding.tvMovieTitle.text = item.title
        binding.tvGenre.text = item.genres[1].toString()
        binding.tvLanguage.text = item.language
        binding.tvRelease.text = item.releaseDate
        binding.tvOverview.text = item.overview
    }

}
