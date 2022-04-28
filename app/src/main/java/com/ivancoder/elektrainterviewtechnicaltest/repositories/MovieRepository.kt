package com.ivancoder.elektrainterviewtechnicaltest.repositories

import com.ivancoder.elektrainterviewtechnicaltest.config.EndPoints
import com.ivancoder.elektrainterviewtechnicaltest.config.Resource
import com.ivancoder.elektrainterviewtechnicaltest.config.ResponseHandler
import com.ivancoder.elektrainterviewtechnicaltest.responses.VideoMovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val endPoints: EndPoints,
                                          private val responseHandler: ResponseHandler
) {

    suspend fun getVideosMovie(movieId:Int): Resource<List<VideoMovieResponse>> {
        return try {
            return endPoints.getVideosMovie(movieId).let {
                if(it.isSuccessful){
                    if(it.code() == 200){
                        responseHandler.handleSuccess(it.body()?.results)
                    }else{
                        responseHandler.handleError("Error ${it.code()}",null)
                    }
                }else{
                    responseHandler.handleError("Ocurrio un error al realizar la peticion",null)
                }
            }
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }
}