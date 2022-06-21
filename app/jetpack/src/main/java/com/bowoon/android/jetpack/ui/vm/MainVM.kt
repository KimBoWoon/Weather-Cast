package com.bowoon.android.jetpack.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.domain.usecase.WeatherCastUseCase
import com.bowoon.android.jetpack.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val weatherCastUseCase: WeatherCastUseCase
) : BaseVM() {
    val weatherInfoList = MutableLiveData<List<WeatherInfo?>>()

    init {
        viewModelScope.launch {
            val weatherList = geocoding.take(10).map {
                weatherCastUseCase.getWeather(it.lat, it.lon)
            }
            weatherInfoList.postValue(weatherList)
        }
    }
}