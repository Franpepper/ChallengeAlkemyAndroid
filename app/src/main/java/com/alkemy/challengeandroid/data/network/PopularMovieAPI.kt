package com.alkemy.challengeandroid.data.network

import com.alkemy.challengeandroid.data.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMovieAPI {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<ResponseModel>
}
