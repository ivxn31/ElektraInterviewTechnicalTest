package com.ivancoder.elektrainterviewtechnicaltest.presentation.di

import com.ivancoder.elektrainterviewtechnicaltest.BuildConfig
import com.ivancoder.elektrainterviewtechnicaltest.presentation.network.ApiKeyInterceptor
import com.ivancoder.elektrainterviewtechnicaltest.config.Consts
import com.ivancoder.elektrainterviewtechnicaltest.presentation.network.EndPoints
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }else{
        OkHttpClient
            .Builder()
            .readTimeout(Consts.END_POINTS.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Consts.END_POINTS.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): EndPoints = Retrofit.Builder()
        .baseUrl(Consts.END_POINTS.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(EndPoints::class.java)
}