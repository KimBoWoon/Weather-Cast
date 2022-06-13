package com.bowoon.android.data.di

import com.bowoon.android.data.service.WeatherCastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): WeatherCastService = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
//        .addConverterFactory()
        .client(client)
        .build()
        .create(WeatherCastService::class.java)

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().apply {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        addNetworkInterceptor(interceptor)
    }.build()
}