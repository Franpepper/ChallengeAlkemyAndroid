package com.alkemy.challengeandroid.data.model

import com.google.gson.annotations.SerializedName


data class PopularMovies(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("title") val title: String?
)
