package com.bowoon.android.weather_cast.ui.vh

import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.R
import com.bowoon.android.weather_cast.databinding.IncludeWeatherConditionBinding
import com.bowoon.android.weather_cast.databinding.VhWeatherInfoBinding
import com.bowoon.android.weather_cast.util.Log
import com.bowoon.android.weather_cast.util.ViewInflater
import com.bumptech.glide.Glide

class WeatherInfoVH(
    private val binding: VhWeatherInfoBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(weatherInfo: WeatherInfo?) {
        runCatching {
            weatherInfo ?: throw RuntimeException("WeatherInfoVH weatherInfo is null")

            binding.apply {
                tvCity.text = weatherInfo.name
                tvTemperature.text = weatherInfo.main?.temp.toString()
                weatherInfo.weather?.forEachIndexed { _, weather ->
                    val weatherConditionBinding by ViewInflater<IncludeWeatherConditionBinding>(
                        LayoutInflater.from(binding.root.context),
                        R.layout.include_weather_condition,
                        llWeatherConditionGroup,
                        false
                    ) {
                        tvWeatherInfo.text = weather?.main

                        Glide.with(ivWeatherImage)
                            .load("https://openweathermap.org/img/wn/${weather?.icon}.png")
                            .into(ivWeatherImage)
                    }

                    llWeatherConditionGroup.addView(weatherConditionBinding.root)
                }
                ivWeatherBg.setBackgroundColor(
                    ResourcesCompat.getColor(
                        binding.root.resources,
                        R.color.purple_200,
                        null
                    )
                )
            }
        }.onSuccess {
            Log.d("WeatherInfoVH success")
        }.onFailure {
            Log.d("WeatherInfoVH failure >>>>> ${it.message}")
        }
    }
}