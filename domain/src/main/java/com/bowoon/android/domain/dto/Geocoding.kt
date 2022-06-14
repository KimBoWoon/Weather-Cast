package com.bowoon.android.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geocoding(
    val lat: Float,
    val lon: Float
) : Parcelable