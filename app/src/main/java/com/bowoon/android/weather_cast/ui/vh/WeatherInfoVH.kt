package com.bowoon.android.weather_cast.ui.vh

import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.R
import com.bowoon.android.weather_cast.databinding.VhWeatherInfoBinding
import com.bowoon.android.weather_cast.util.Log

class WeatherInfoVH(
    private val binding: VhWeatherInfoBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(weatherInfo: WeatherInfo?) {
        runCatching {
            weatherInfo ?: throw RuntimeException("WeatherInfoVH weatherInfo is null")

            binding.apply {
                tvCity.text = weatherInfo.name
                tvTemperature.text = weatherInfo.main?.temp.toString()
                weatherInfo.weather?.forEach {
                    tvWeatherInfo.text = if (tvWeatherInfo.text.isEmpty()) {
                        it?.main
                    } else {
                        String.format("%s\n%s", tvWeatherInfo.text, it?.main)
                    }
                }
                ivWeatherBg.setBackgroundColor(ResourcesCompat.getColor(binding.root.resources, R.color.purple_200, null))
            }
        }.onSuccess {
            Log.d("WeatherInfoVH success")
        }.onFailure {
            Log.d("WeatherInfoVH failure")
        }
    }
}