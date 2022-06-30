package com.bowoon.android.jetpack.ui.vh

import android.graphics.Color
import androidx.core.view.isVisible
import com.bowoon.android.data.util.Log
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.jetpack.base.BaseVH
import com.bowoon.android.jetpack.databinding.VhWeatherInfoBinding
import com.bowoon.android.jetpack.ui.adapter.WeatherConditionAdapter
import kotlin.random.Random

class WeatherInfoVH(
    override val binding: VhWeatherInfoBinding
) : BaseVH<VhWeatherInfoBinding, WeatherInfo>(binding) {
    private val random = Random(System.currentTimeMillis())
    private val rgb = Color.argb(127, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    private var expanded = false

    override fun bind(weatherInfo: WeatherInfo?) {
        runCatching {
            weatherInfo ?: throw RuntimeException("WeatherInfoVH weatherInfo is null")

            binding.apply {
                this.weatherInfo = weatherInfo

                rvWeatherCondition.apply {
                    adapter = WeatherConditionAdapter(weatherInfo.weather)
                }
                ivWeatherBg.setBackgroundColor(rgb)

                root.setOnClickListener {
                    expanded = !expanded
                    clExpandGroup.isVisible = expanded
                    executePendingBindings()
                }
            }
        }.onSuccess {
            Log.d("WeatherInfoVH success")
        }.onFailure {
            Log.d("WeatherInfoVH failure >>>>> ${it.message}")
        }
    }
}