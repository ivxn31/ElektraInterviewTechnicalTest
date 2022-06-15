package com.ivancoder.elektrainterviewtechnicaltest.presentation.network.responses

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page:Int,
    @SerializedName("results")
    val movieList:ArrayList<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages:Int,
    @SerializedName("total_results")
    val totalResults:Int,
)