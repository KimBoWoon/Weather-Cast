package com.bowoon.android.weather_cast.ui.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.databinding.VhWeatherInfoBinding
import com.bowoon.android.weather_cast.ui.vh.WeatherInfoVH
import com.bowoon.android.weather_cast.util.Log
import com.bowoon.android.weather_cast.util.dp

class WeatherCastAdapter(
    private val items: List<WeatherInfo?>? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        WeatherInfoVH(
            VhWeatherInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items?.let { weatherInfoList ->
            when (holder) {
                is WeatherInfoVH -> {
                    holder.bind(weatherInfoList[position])
                }
                else -> {
                    Log.e("viewholder not found")
                }
            }
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class WeatherListDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val index = parent.getChildAdapterPosition(view)
            val lastIndex = (parent.adapter as? WeatherCastAdapter)?.items?.lastIndex

            outRect.left = 10.dp
            outRect.right = 10.dp

            when (index) {
                0 -> {
                    outRect.top = 10.dp
                    outRect.bottom = 5.dp
                }
                lastIndex -> {
                    outRect.top = 5.dp
                    outRect.bottom = 10.dp
                }
                else -> {
                    outRect.top = 5.dp
                    outRect.bottom = 5.dp
                }
            }
        }
    }
}