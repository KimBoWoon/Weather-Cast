package com.bowoon.android.data.service

import com.bowoon.android.data.BuildConfig
import com.bowoon.android.domain.dto.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherCastService {
    @GET("/data/2.5/weather?")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("lang") lang: String = "en",
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = BuildConfig.openWeatherKey
    ): WeatherInfo
}