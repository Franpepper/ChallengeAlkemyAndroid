package com.alkemy.challengeandroid.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkemy.challengeandroid.core.RetrofitHelper
import com.alkemy.challengeandroid.data.model.DetailedMovie
import com.alkemy.challengeandroid.data.network.DetailedMovieAPI
import com.alkemy.challengeandroid.databinding.ActivityDetailBinding
import com.alkemy.challengeandroid.ui.viewmodel.DetailedMovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Detail (private val id: Int) : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter : DetailedMovieAdapter
    private lateinit var movieData : DetailedMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        initRecyclerView()
        getDetail()
    }

    private fun initRecyclerView() {

        adapter = DetailedMovieAdapter(movieData)
        binding.rvDetail.layoutManager = LinearLayoutManager(this)
        binding.rvDetail.adapter = adapter
    }

    private fun getDetail(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(DetailedMovieAPI::class.java)
                .getMovieDetails(id,apiKey = "98dc5f487975d353ce06e501e6dfde8c")
            val movie = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    movieData = movie!!
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@Detail, "Ocurrio un error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}