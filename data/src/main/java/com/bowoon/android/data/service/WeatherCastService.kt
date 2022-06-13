package com.bowoon.android.data.service

import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherCastService {
    @GET("/data/3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}")
    fun getWeather(@Path("lat") lat: Float, @Path("lon") lon: Float, @Path("part") part: String)
}