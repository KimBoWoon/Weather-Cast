package com.bowoon.android.weather_cast

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bowoon.android.domain.usecase.WeatherCastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val weatherCastUseCase: WeatherCastUseCase
) : ViewModel(), LifecycleObserver {
    init {
        viewModelScope.launch {
            val weatherInfo = weatherCastUseCase.getWeather(37.5666805f, 126.9784147f)
            Log.d("MainVM", weatherInfo.toString())
        }
    }
}