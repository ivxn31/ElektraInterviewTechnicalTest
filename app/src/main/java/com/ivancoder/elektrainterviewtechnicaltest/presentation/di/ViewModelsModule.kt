package com.ivancoder.elektrainterviewtechnicaltest.presentation.di

import com.ivancoder.elektrainterviewtechnicaltest.data.repositories.MovieRepository
import com.ivancoder.elektrainterviewtechnicaltest.data.repositories.MoviesRepository
import com.ivancoder.elektrainterviewtechnicaltest.presentation.viewmodels.MovieViewModel
import com.ivancoder.elektrainterviewtechnicaltest.presentation.viewmodels.MoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ViewModelsModule {
    @Provides
    @Singleton
    fun provideMoviesViewModel(repository: MoviesRepository) = MoviesViewModel(repository)

    @Provides
    @Singleton
    fun provideMovieViewModel(repository: MovieRepository) = MovieViewModel(repository)
}