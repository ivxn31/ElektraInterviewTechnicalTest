package com.ivancoder.elektrainterviewtechnicaltest.presentation.di

import android.app.Application
import android.content.Context
import com.ivancoder.elektrainterviewtechnicaltest.config.DatabaseService
import com.ivancoder.elektrainterviewtechnicaltest.config.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideResponseHandler() = ResponseHandler()

    @Singleton
    @Provides
    fun provideMovieDao(@ApplicationContext context: Context) = DatabaseService.getInstance(context).movieDao()
}