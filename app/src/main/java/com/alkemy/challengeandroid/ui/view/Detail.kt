package com.alkemy.challengeandroid.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alkemy.challengeandroid.core.RetrofitHelper
import com.alkemy.challengeandroid.data.model.DetailedMovie
import com.alkemy.challengeandroid.data.network.DetailedMovieAPI
import com.alkemy.challengeandroid.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Detail () : AppCompatActivity() {

     private lateinit var binding: ActivityDetailBinding
     //private lateinit var adapter : DetailedMovieAdapter
     private var movieData = DetailedMovie(id = 0, title = "", overview = "", posterPath = "",
        language = "", releaseDate = "0000-00-00", voteAverage = 0.0)
     private var id : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra("id", 0)
        initComponents()
        getDetail()

    }

    private fun initComponents() {
        var poster = binding.ivMovie
        Picasso.get().load("https://image.tmdb.org/t/p/original".plus(movieData.posterPath))
            .into(poster)
        binding.tvMovieTitle.text = movieData.title
        binding.tvLanguage.text = "Language:\n".plus(languageString(movieData.language))
        binding.tvVoteAverage.text = "Vote Average:\n".plus(movieData.voteAverage)
        binding.tvRelease.text = "Release Date:\n".plus(movieData.releaseDate)
        binding.tvOverview.text = "Overview:\n".plus(movieData.overview)

    }
    private fun languageString(lang : String): String {
        when(lang){
            "en" -> return "English"
            "es" -> return "Spanish"
            "fr" -> return "French"
            "it" -> return "Italian"
            "de" -> return "German"
            "ja" -> return "Japanese"
            "pt" -> return "Portuguese"
            "ru" -> return "Russian"
            "zh" -> return "Chinese"
            else -> return "Unknown"
        }
    }
    private fun getDetail(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(DetailedMovieAPI::class.java)
                .getMovieDetails(id,apiKey = "98dc5f487975d353ce06e501e6dfde8c")
            val movie = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    movieData = movie!!
                    initComponents()
                } else {
                    Toast.makeText(this@Detail, "Ocurrio un error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}