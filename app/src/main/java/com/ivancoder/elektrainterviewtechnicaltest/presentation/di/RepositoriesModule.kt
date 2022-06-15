package com.ivancoder.elektrainterviewtechnicaltest.presentation.di

import com.ivancoder.elektrainterviewtechnicaltest.presentation.network.EndPoints
import com.ivancoder.elektrainterviewtechnicaltest.config.ResponseHandler
import com.ivancoder.elektrainterviewtechnicaltest.presentation.persistence.db.daos.MovieDao
import com.ivancoder.elektrainterviewtechnicaltest.data.repositories.MovieRepository
import com.ivancoder.elektrainterviewtechnicaltest.data.repositories.MoviesRepository
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