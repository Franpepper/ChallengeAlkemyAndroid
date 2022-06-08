package com.alkemy.challengeandroid.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkemy.challengeandroid.R
import com.alkemy.challengeandroid.core.RetrofitHelper
import com.alkemy.challengeandroid.data.model.PopularMovies
import com.alkemy.challengeandroid.data.network.PopularMovieAPI
import com.alkemy.challengeandroid.databinding.ActivityMainBinding
import com.alkemy.challengeandroid.databinding.PosterBinding
import com.alkemy.challengeandroid.ui.viewmodel.PopularMoviesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PopularMoviesAdapter
    private val movieData = mutableListOf<PopularMovies>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getMovies()
    }

    private fun initRecyclerView() {

        adapter = PopularMoviesAdapter(movieData)
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.adapter = adapter

    }

    private fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(PopularMovieAPI::class.java)
                .getPopularMovies(apiKey = "98dc5f487975d353ce06e501e6dfde8c")
            val movies = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val posters = movies?.results ?: emptyList()
                    movieData.clear()
                    movieData.addAll(posters)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

