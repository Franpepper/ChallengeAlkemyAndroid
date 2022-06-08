package com.alkemy.challengeandroid.data.model

import com.google.gson.annotations.SerializedName

data class DetailedMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_data") val releaseDate: String,
    @SerializedName("original_language") val language: String,
    @SerializedName("genres") val genres: List<Genre>
)

