package com.bowoon.android.jetpack.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bowoon.android.jetpack.R
import com.bowoon.android.jetpack.base.BaseActivity
import com.bowoon.android.jetpack.databinding.ActivityMainBinding
import com.bowoon.android.jetpack.ui.adapter.WeatherCastAdapter
import com.bowoon.android.jetpack.ui.vm.MainVM
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