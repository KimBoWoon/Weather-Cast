package com.bowoon.android.compose.di

import com.bowoon.android.data.repository.WeatherCastRepositoryImpl
import com.bowoon.android.data.service.WeatherCastService
import com.bowoon.android.domain.repository.WeatherCastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideWeatherCastRepository(
        weatherCastService: WeatherCastService
    ): WeatherCastRepository = WeatherCastRepositoryImpl(weatherCastService)
}