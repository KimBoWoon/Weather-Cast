package com.bowoon.android.jetpack.ui.vm

import androidx.lifecycle.viewModelScope
import com.bowoon.android.data.util.Status
import com.bowoon.android.domain.usecase.WeatherCastUseCase
import com.bowoon.android.jetpack.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val weatherCastUseCase: WeatherCastUseCase
) : BaseVM() {
    val weatherInfoList = MutableStateFlow<Status>(Status.Loading)

    init {
        viewModelScope.launch {
            runCatching {
                geocoding.shuffled().take(20).map {
                    weatherCastUseCase.getWeather(it.lat, it.lon)
                }
            }.onSuccess { weatherList ->
                weatherInfoList.value = Status.Success(weatherList)
            }.onFailure { e ->
                weatherInfoList.value = Status.Failure(e.message ?: "something wrong")
            }
        }
    }
}