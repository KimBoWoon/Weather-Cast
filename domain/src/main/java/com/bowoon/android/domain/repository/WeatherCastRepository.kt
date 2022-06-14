package com.bowoon.android.domain.repository

import com.bowoon.android.domain.dto.WeatherInfo

interface WeatherCastRepository {
    suspend fun getWeather(
        lat: Float,
        lon: Float
    ): WeatherInfo
}