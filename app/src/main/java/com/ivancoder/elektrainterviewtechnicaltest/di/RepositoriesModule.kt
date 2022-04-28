package com.ivancoder.elektrainterviewtechnicaltest.di

import com.ivancoder.elektrainterviewtechnicaltest.config.EndPoints
import com.ivancoder.elektrainterviewtechnicaltest.config.ResponseHandler
import com.ivancoder.elektrainterviewtechnicaltest.daos.MovieDao
import com.ivancoder.elektrainterviewtechnicaltest.repositories.MovieRepository
import com.ivancoder.elektrainterviewtechnicaltest.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RepositoriesModule {

    @Provides
    fun provideMoviesRepository(endpoint: EndPoints,
                                movieDao: MovieDao,
                                handler: ResponseHandler
    ) = MoviesRepository(endpoint,movieDao,handler)

    @Provides
    fun provideMovieRepository(endpoint: EndPoints, handler: ResponseHandler
    ) = MovieRepository(endpoint,handler)
}