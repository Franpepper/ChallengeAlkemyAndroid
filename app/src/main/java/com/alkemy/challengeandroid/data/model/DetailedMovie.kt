package com.alkemy.challengeandroid.data.model

import com.google.gson.annotations.SerializedName

data class DetailedMovie(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("original_language") val language: String,
    @SerializedName("vote_average") val voteAverage: Double
)

