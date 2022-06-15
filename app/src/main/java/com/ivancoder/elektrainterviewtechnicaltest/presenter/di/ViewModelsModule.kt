package com.ivancoder.elektrainterviewtechnicaltest.presenter.di

import com.ivancoder.elektrainterviewtechnicaltest.data.repositories.MovieRepository
import com.ivancoder.elektrainterviewtechnicaltest.data.repositories.MoviesRepository
import com.ivancoder.elektrainterviewtechnicaltest.presenter.viewmodels.MovieViewModel
import com.ivancoder.elektrainterviewtechnicaltest.presenter.viewmodels.MoviesViewModel
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