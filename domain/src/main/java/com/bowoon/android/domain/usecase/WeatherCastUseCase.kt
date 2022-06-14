package com.bowoon.android.domain.usecase

import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.domain.repository.WeatherCastRepository

class WeatherCastUseCase(
    private val weatherCastRepository: WeatherCastRepository
) {
    suspend fun getWeather(lat: Float, lon: Float): WeatherInfo =
        weatherCastRepository.getWeather(lat, lon)
}