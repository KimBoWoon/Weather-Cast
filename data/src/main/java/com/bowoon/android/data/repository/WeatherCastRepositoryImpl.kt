package com.bowoon.android.data.repository

import com.bowoon.android.data.service.WeatherCastService
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.domain.repository.WeatherCastRepository

class WeatherCastRepositoryImpl(
    private val weatherCastService: WeatherCastService
) : WeatherCastRepository {
    override suspend fun getWeather(lat: Float, lon: Float): WeatherInfo =
        weatherCastService.getWeather(lat, lon)
}