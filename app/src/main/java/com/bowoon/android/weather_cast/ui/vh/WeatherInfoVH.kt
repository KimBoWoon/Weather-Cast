package com.bowoon.android.weather_cast.ui.vh

import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.R
import com.bowoon.android.weather_cast.databinding.VhWeatherInfoBinding

class WeatherInfoVH(
    private val binding: VhWeatherInfoBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(weatherInfo: WeatherInfo) {
        binding.apply {
            tvCity.text = weatherInfo.name
            tvTemperature.text = weatherInfo.main?.temp.toString()
            tvWeatherInfo.text = weatherInfo.weather?.firstOrNull()?.description
            ivWeatherBg.setBackgroundColor(ResourcesCompat.getColor(binding.root.resources, R.color.purple_200, null))
        }
    }
}