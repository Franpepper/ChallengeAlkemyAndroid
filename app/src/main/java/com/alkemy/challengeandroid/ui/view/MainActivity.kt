package com.alkemy.challengeandroid.ui.view


import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkemy.challengeandroid.core.RetrofitHelper
import com.alkemy.challengeandroid.data.model.PopularMovies
import com.alkemy.challengeandroid.data.network.PopularMovieAPI
import com.alkemy.challengeandroid.databinding.ActivityMainBinding
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
        checkConnection()
    }

    private fun initRecyclerView() {

        adapter = PopularMoviesAdapter(movieData)
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.adapter = adapter

    }

    private fun checkConnection() {
        val conManager : ConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo : NetworkInfo? = conManager.activeNetworkInfo
        val isConnected : Boolean = netInfo?.isConnectedOrConnecting == true
        if(!isConnected) {
            Toast.makeText(this, "No internet connection, please verify your connection, and try again ",
                Toast.LENGTH_LONG).show()
            noInternetMenu()
        }else
            getMovies()
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
                    Toast.makeText(this@MainActivity, "An error occurred ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    private fun noInternetMenu(){
        //Making the menu visible
        binding.tvNoInternet.visibility = android.view.View.VISIBLE
        binding.btnRetry.visibility = android.view.View.VISIBLE
        binding.btnExit.visibility = android.view.View.VISIBLE

        binding.tvNoInternet.text = "No internet connection, please verify your connection, and try again "
        binding.btnRetry.setOnClickListener(){
            //Making the menu invisible
            binding.tvNoInternet.visibility = android.view.View.INVISIBLE
            binding.btnRetry.visibility = android.view.View.INVISIBLE
            binding.btnExit.visibility = android.view.View.INVISIBLE
            checkConnection()
        }
        binding.btnExit.setOnClickListener(){
            exitApp()
        }
    }

    private fun exitApp() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            Toast.makeText(this, "Exiting app", Toast.LENGTH_SHORT).show()
            finish()
        }, 1000)

    }

}


