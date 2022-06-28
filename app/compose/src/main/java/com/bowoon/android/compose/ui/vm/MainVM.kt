package com.bowoon.android.compose.ui.vm

import androidx.lifecycle.viewModelScope
import com.bowoon.android.compose.base.BaseVM
import com.bowoon.android.compose.util.Status
import com.bowoon.android.domain.usecase.WeatherCastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val weatherCastUseCase: WeatherCastUseCase
) : BaseVM() {
    val weatherInfoStatus = MutableStateFlow<Status>(Status.Loading)

    init {
        viewModelScope.launch {
            runCatching {
                geocoding.shuffled().take(20).map { geo ->
                    weatherCastUseCase.getWeather(geo.lat, geo.lon)
                }
            }.onSuccess { weatherList ->
                weatherInfoStatus.value = Status.Success(weatherList)
            }.onFailure { e ->
                weatherInfoStatus.value = Status.Failure(e.message ?: "something error")
            }
        }
    }
}