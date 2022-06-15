package com.ivancoder.elektrainterviewtechnicaltest.data.repositories

import com.ivancoder.elektrainterviewtechnicaltest.presentation.network.EndPoints
import com.ivancoder.elektrainterviewtechnicaltest.config.Resource
import com.ivancoder.elektrainterviewtechnicaltest.config.ResponseHandler
import com.ivancoder.elektrainterviewtechnicaltest.presentation.persistence.db.daos.MovieDao
import com.ivancoder.elektrainterviewtechnicaltest.presentation.persistence.db.entities.MovieEntity
import com.ivancoder.elektrainterviewtechnicaltest.domain.enums.TypeFilterMovie
import com.ivancoder.elektrainterviewtechnicaltest.presentation.network.responses.MoviesResponse
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val endPoints: EndPoints,
                                           private val movieDao: MovieDao,
                                           private val responseHandler: ResponseHandler
){
    suspend fun getMoviesRemote(typeFilter: TypeFilterMovie): Resource<MoviesResponse> {
        return try {
            when(typeFilter){
                TypeFilterMovie.NOW_PLAYING -> getMorePopularMoviesRemote()
                TypeFilterMovie.MORE_POPULAR -> getNowPlayingMoviesRemote()
            }
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }

    private suspend fun getNowPlayingMoviesRemote(): Resource<MoviesResponse> {
        return try {
            return endPoints.getNowPlayingMovies(1).let {
                if(it.isSuccessful){
                    if(it.code() == 200){
                        responseHandler.handleSuccess(it.body())
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

    private suspend fun getMorePopularMoviesRemote(): Resource<MoviesResponse> {
        return try {
            return endPoints.getMorePopularMovies(1).let {
                if(it.isSuccessful){
                    if(it.code() == 200){
                        responseHandler.handleSuccess(it.body())
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

    suspend fun getMoviesCache(typeFilter: TypeFilterMovie) = when(typeFilter){
        TypeFilterMovie.NOW_PLAYING -> getNowPlayingMoviesCache()
        TypeFilterMovie.MORE_POPULAR -> getMorePopularMoviesCache()
    }

    private suspend fun getNowPlayingMoviesCache() = ArrayList(movieDao.getMovies(false).map { it.toModel() })

    private suspend fun getMorePopularMoviesCache() = ArrayList(movieDao.getMovies().map { it.toModel() })

    suspend fun insertMultiple(typeFilter: TypeFilterMovie,movies: List<MovieEntity>?){
        if(hasMoviesForTypeFilter(typeFilter)){
            deleteMoviesCacheForTypeFilter(typeFilter)
        }
        movieDao.insertMultiple(movies)
    }

    private suspend fun hasMoviesForTypeFilter(typeFilter: TypeFilterMovie):Boolean = when(typeFilter){
        TypeFilterMovie.NOW_PLAYING -> movieDao.getMovies(false).isNotEmpty()
        TypeFilterMovie.MORE_POPULAR -> movieDao.getMovies().isNotEmpty()
    }

    private suspend fun deleteMoviesCacheForTypeFilter(typeFilter: TypeFilterMovie) = when(typeFilter){
        TypeFilterMovie.NOW_PLAYING -> movieDao.deleteMovies(false)
        TypeFilterMovie.MORE_POPULAR -> movieDao.deleteMovies()
    }
}