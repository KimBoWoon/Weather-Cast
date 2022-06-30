package com.bowoon.android.jetpack.util

import android.content.res.Resources
import kotlin.math.roundToInt

const val NONE = "NONE"
const val ALPHA = (255 / 2.5).toInt()

val Int.dp: Int get() = (this.toFloat() * Resources.getSystem().displayMetrics.density).roundToInt()

//fun getDegree(deg: Float): String = when (deg) {
//    in 337.5f..360f, 0f -> "N"
//    in 0f..22.5f -> "NNE"
//    in 22.5f..45f -> "NE"
//    in 45f..67.5f -> "ENE"
//    in 67.5f..90f -> "E"
//    in 90f..112.5f -> "ESE"
//    in 112.5f..135f -> "SE"
//    in 135f..157.5f -> "SSE"
//    in 157.5f..180f -> "S"
//    in 180f..202.5f -> "SSW"
//    in 202.5f..225f -> "SW"
//    in 225f..247.5f -> "WSW"
//    in 247.5f..270f -> "W"
//    in 270f..292.5f -> "WNW"
//    in 292.5f..315f -> "NW"
//    in 315f..337.6f -> "NNW"
//    else -> NONE
//}

fun getDegree(degree: Int): String {
    val result = ((degree + 22.5 * 0.5) / 22.5).toInt()
    val windType: WindType = WindType.values()[result]
    return windType.direction
}

enum class WindType(
    val code: Int,
    val direction: String
) {
    N0(0, "북"),
    NNE(1, "북북동"),
    NE(2, "북동"),
    ENE(3, "동북동"),
    E(4, "동"),
    ESE(5, "동남동"),
    SE(6, "남동"),
    SSE(7, "남남동"),
    S(8, "남"),
    SSW(9, "남남서"),
    SW(10, "남서"),
    WSW(11, "서남서"),
    W(12, "서"),
    WNW(13, "서북서"),
    NW(14, "북서"),
    NNW(15, "북북서"),
    N16(16, "북"),
    NONE(17, "NONE");
}