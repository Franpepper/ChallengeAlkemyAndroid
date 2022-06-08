package com.alkemy.challengeandroid.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
            @SerializedName("results") val results: List<PopularMovies>
)
