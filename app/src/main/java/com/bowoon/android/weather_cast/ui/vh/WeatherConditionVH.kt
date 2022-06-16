package com.bowoon.android.weather_cast.ui.vh

import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.base.BaseVH
import com.bowoon.android.weather_cast.databinding.VhWeatherConditionBinding
import com.bowoon.android.weather_cast.util.Log

class WeatherConditionVH(
    override val binding: VhWeatherConditionBinding
) : BaseVH<VhWeatherConditionBinding, WeatherInfo.Weather>(binding) {
    override fun bind(weather: WeatherInfo.Weather?) {
        runCatching {
            binding.apply {
                this.weather = weather
            }
        }.onSuccess {
            Log.d("WeatherConditionVH success")
        }.onFailure {
            Log.d("WeatherConditionVH failure >>>>> ${it.message}")
        }
    }
}