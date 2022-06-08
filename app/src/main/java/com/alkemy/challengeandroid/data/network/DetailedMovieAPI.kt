package com.alkemy.challengeandroid.data.network

import com.alkemy.challengeandroid.data.model.DetailedMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailedMovieAPI {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailedMovie>
}