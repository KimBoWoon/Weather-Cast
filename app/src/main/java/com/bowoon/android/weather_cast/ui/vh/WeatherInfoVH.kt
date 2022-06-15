package com.bowoon.android.weather_cast.ui.vh

import android.graphics.Color
import android.view.LayoutInflater
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.R
import com.bowoon.android.weather_cast.base.BaseVH
import com.bowoon.android.weather_cast.databinding.IncludeWeatherConditionBinding
import com.bowoon.android.weather_cast.databinding.VhWeatherInfoBinding
import com.bowoon.android.weather_cast.util.Log
import com.bowoon.android.weather_cast.util.ViewInflater
import kotlin.random.Random

class WeatherInfoVH(
    override val binding: VhWeatherInfoBinding
) : BaseVH<VhWeatherInfoBinding, WeatherInfo>(binding) {
    override fun bind(weatherInfo: WeatherInfo?) {
        runCatching {
            weatherInfo ?: throw RuntimeException("WeatherInfoVH weatherInfo is null")

            binding.apply {
                this.weatherInfo = weatherInfo

                weatherInfo.weather?.forEachIndexed { _, weather ->
                    val weatherConditionBinding by ViewInflater<IncludeWeatherConditionBinding>(
                        LayoutInflater.from(binding.root.context),
                        R.layout.include_weather_condition,
                        llWeatherConditionGroup,
                        false
                    ) {
                        this.weather = weather
                    }

                    llWeatherConditionGroup.addView(weatherConditionBinding.root)
                }
                val random = Random(System.currentTimeMillis())
                val rgb = Color.argb(127, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                ivWeatherBg.setBackgroundColor(rgb)
            }
        }.onSuccess {
            Log.d("WeatherInfoVH success")
        }.onFailure {
            Log.d("WeatherInfoVH failure >>>>> ${it.message}")
        }
    }
}