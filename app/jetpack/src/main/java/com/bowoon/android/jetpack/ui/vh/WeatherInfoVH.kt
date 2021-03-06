package com.bowoon.android.jetpack.ui.vh

import android.graphics.Color
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.jetpack.base.BaseVH
import com.bowoon.android.jetpack.databinding.VhWeatherInfoBinding
import com.bowoon.android.jetpack.ui.adapter.WeatherConditionAdapter
import com.bowoon.android.jetpack.util.Log
import kotlin.random.Random

class WeatherInfoVH(
    override val binding: VhWeatherInfoBinding
) : BaseVH<VhWeatherInfoBinding, WeatherInfo>(binding) {
    private val random = Random(System.currentTimeMillis())
    private val rgb = Color.argb(127, random.nextInt(256), random.nextInt(256), random.nextInt(256))

    override fun bind(weatherInfo: WeatherInfo?) {
        runCatching {
            weatherInfo ?: throw RuntimeException("WeatherInfoVH weatherInfo is null")

            binding.apply {
                this.weatherInfo = weatherInfo

                rvWeatherCondition.apply {
                    adapter = WeatherConditionAdapter(weatherInfo.weather)
                }
                ivWeatherBg.setBackgroundColor(rgb)
            }
        }.onSuccess {
            Log.d("WeatherInfoVH success")
        }.onFailure {
            Log.d("WeatherInfoVH failure >>>>> ${it.message}")
        }
    }
}