package com.bowoon.android.weather_cast.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.weather_cast.R
import com.bowoon.android.weather_cast.base.BaseActivity
import com.bowoon.android.weather_cast.databinding.ActivityMainBinding
import com.bowoon.android.weather_cast.ui.adapter.WeatherCastAdapter
import com.bowoon.android.weather_cast.ui.vm.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    private val viewModel by viewModels<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@MainActivity
        }
        lifecycle.addObserver(viewModel)

        initBinding()
        initLiveData()
    }

    override fun initBinding() {
        binding.apply {
            rvWeatherInfoList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                if (itemDecorationCount == 0) {
                    addItemDecoration(WeatherCastAdapter.WeatherListDecoration())
                }
            }
        }
    }

    override fun initLiveData() {
        viewModel.weatherInfoList.observe(this) { weatherInfoList ->
            binding.pbLoading.isVisible = false
            binding.rvWeatherInfoList.apply {
                adapter = WeatherCastAdapter(weatherInfoList)
            }
        }
    }
}