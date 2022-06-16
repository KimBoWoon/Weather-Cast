package com.bowoon.android.weather_cast.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.databinding.VhWeatherConditionBinding
import com.bowoon.android.weather_cast.ui.vh.WeatherConditionVH
import com.bowoon.android.weather_cast.util.Log

class WeatherConditionAdapter(
    private val items: List<WeatherInfo.Weather?>? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        WeatherConditionVH(
            VhWeatherConditionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items?.let { weatherInfoList ->
            when (holder) {
                is WeatherConditionVH -> {
                    holder.bind(weatherInfoList[position])
                }
                else -> {
                    Log.e("viewholder not found")
                }
            }
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0
}