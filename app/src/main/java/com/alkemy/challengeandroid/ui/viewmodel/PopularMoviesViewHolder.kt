package com.alkemy.challengeandroid.ui.viewmodel

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.challengeandroid.data.model.PopularMovies
import com.alkemy.challengeandroid.databinding.PosterBinding
import com.alkemy.challengeandroid.ui.view.Detail
import com.squareup.picasso.Picasso



class PopularMoviesViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val binding = PosterBinding.bind(view)

    fun bind(item: PopularMovies) {
        val url = "https://image.tmdb.org/t/p/original".plus(item.posterPath)
        Picasso.get().load(url).into(binding.ivMovie)
        binding.tvTitle.text = item.title

        binding.btnInfo.setOnClickListener {

        val intent = Intent(binding.ivMovie.context, Detail::class.java)
        intent.putExtra("id", item.id)
        binding.ivMovie.context.startActivity(intent)
        }
    }

    private fun startActivity(intent: Intent) {
        binding.ivMovie.context.startActivity(intent)
    }
}


