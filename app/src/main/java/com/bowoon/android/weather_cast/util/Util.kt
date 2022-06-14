package com.bowoon.android.weather_cast.util

import android.content.res.Resources
import kotlin.math.roundToInt

val Int.dp: Int get() = (this.toFloat() * Resources.getSystem().displayMetrics.density).roundToInt()