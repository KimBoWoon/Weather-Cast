package com.bowoon.android.domain.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Geocoding(
    val lat: Float,
    val lon: Float
) : Parcelable