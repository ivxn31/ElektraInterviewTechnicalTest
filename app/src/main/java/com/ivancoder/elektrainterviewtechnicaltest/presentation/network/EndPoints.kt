package com.ivancoder.elektrainterviewtechnicaltest.presentation.network

import com.ivancoder.elektrainterviewtechnicaltest.config.Consts
import com.ivancoder.elektrainterviewtechnicaltest.presentation.network.responses.MovieResponse
import com.ivancoder.elektrainterviewtechnicaltest.presentation.network.responses.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoints {

    @GET(Consts.END_POINTS.GET_NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(@Query("page") page:Int): Response<MoviesResponse>

    @GET(Consts.END_POINTS.GET_MORE_POPULAR_MOVIES)
    suspend fun getMorePopularMovies(@Query("page") page:Int): Response<MoviesResponse>

    @GET(Consts.END_POINTS.GET_VIDEOS_MOVIE)
    suspend fun getVideosMovie(@Path("movie_id") id:Int):Response<MovieResponse>
}