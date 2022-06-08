package com.alkemy.challengeandroid.ui.viewmodel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.challengeandroid.data.model.PopularMovies
import com.alkemy.challengeandroid.databinding.PosterBinding
import com.squareup.picasso.Picasso



class PopularMoviesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = PosterBinding.bind(view)

    fun bind(item: PopularMovies) {
        val url = "https://image.tmdb.org/t/p/original".plus(item.posterPath)
        Picasso.get().load(url).into(binding.ivMovie)
        binding.tvTitle.text = item.title
    }
}