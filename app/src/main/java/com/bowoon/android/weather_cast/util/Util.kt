package com.bowoon.android.weather_cast.util

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import com.bowoon.android.domain.dto.WeatherInfo
import kotlin.math.roundToInt

const val NONE = "NONE"
const val ALPHA = 255 / 2

val Int.dp: Int get() = (this.toFloat() * Resources.getSystem().displayMetrics.density).roundToInt()

fun getWeatherColor(condition: Int): Color = when (condition) {
    in 200..299 -> Color(255, 0, 0, ALPHA)
    in 300..399 -> Color(255, 140, 0, ALPHA)
    in 500..599 -> Color(255, 112, 0, ALPHA)
    in 600..699 -> Color(65, 105, 225, ALPHA)
    in 700..799 -> Color(192, 192, 192, ALPHA)
    in 801..809 -> Color(139, 149, 159, ALPHA)
    800 -> Color(250, 247, 82, ALPHA)
    else -> Color(211, 211, 211, ALPHA)
}

sealed class Status {
    object Loading : Status()
    data class Success(val data: List<WeatherInfo>) : Status()
    data class Failure(val message: String) : Status()
}