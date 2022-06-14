package com.bowoon.android.weather_cast.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bowoon.android.domain.dto.Geocoding
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.domain.usecase.WeatherCastUseCase
import com.bowoon.android.weather_cast.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val weatherCastUseCase: WeatherCastUseCase
) : BaseVM() {
    val weatherInfoList = MutableLiveData<List<WeatherInfo?>>()
    val geocoding = mutableListOf<Geocoding>(
        Geocoding(37.5666805f, 126.9784147f),
        Geocoding(35f, 139f)
    )

    init {
        viewModelScope.launch {
            val weatherList = geocoding.map { geo ->
                weatherCastUseCase.getWeather(geo.lat, geo.lon)
            }
            weatherInfoList.postValue(weatherList)
        }
    }
}