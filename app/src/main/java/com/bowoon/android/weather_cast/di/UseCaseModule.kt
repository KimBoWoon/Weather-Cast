package com.bowoon.android.weather_cast.di

import com.bowoon.android.domain.repository.WeatherCastRepository
import com.bowoon.android.domain.usecase.WeatherCastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideWeatherCastUseCase(
        weatherCastRepository: WeatherCastRepository
    ): WeatherCastUseCase = WeatherCastUseCase(weatherCastRepository)
}