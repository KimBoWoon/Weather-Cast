package com.bowoon.android.weather_cast.util

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt

const val NONE = "NONE"

val Int.dp: Int get() = (this.toFloat() * Resources.getSystem().displayMetrics.density).roundToInt()

fun getWeatherColor(condition: Int): Color = when (condition) {
    in 200..299 -> Color(255, 0, 0, 127)
    in 300..399 -> Color(255, 140, 0, 127)
    in 500..599 -> Color(255, 112, 0, 127)
    in 600..699 -> Color(65, 105, 225, 127)
    in 700..799 -> Color(192, 192, 192, 127)
    in 801..809 -> Color(139, 149, 159, 127)
    800 -> Color(250, 247, 82, 127)
    else -> Color(211, 211, 211, 127)
}