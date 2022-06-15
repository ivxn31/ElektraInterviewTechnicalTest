package com.ivancoder.elektrainterviewtechnicaltest.presenter.network.responses

import com.google.gson.annotations.SerializedName
import com.ivancoder.elektrainterviewtechnicaltest.presenter.persistence.db.entities.MovieEntity

data class MovieResponse(
    val id:Int,
    val overview:String,
    val popularity:Double,
    @SerializedName("poster_path")
    val posterPath:String,
    @SerializedName("release_date")
    val releaseDate:String,
    val title:String,
    @SerializedName("vote_average")
    val voteAverage:Double,
    @SerializedName("vote_count")
    val voteCount:Int,
    val results:ArrayList<VideoMovieResponse>
){
    fun toEntity(isPopular:Boolean) = MovieEntity(id.toLong(),overview,popularity,posterPath,releaseDate,title,voteAverage,voteCount, isPopular)
}
