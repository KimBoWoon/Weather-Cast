package com.bowoon.android.weather_cast.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}