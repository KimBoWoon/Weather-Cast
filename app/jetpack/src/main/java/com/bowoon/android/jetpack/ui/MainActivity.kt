package com.bowoon.android.jetpack.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bowoon.android.data.util.Log
import com.bowoon.android.data.util.Status
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.jetpack.R
import com.bowoon.android.jetpack.base.BaseActivity
import com.bowoon.android.jetpack.databinding.ActivityMainBinding
import com.bowoon.android.jetpack.ui.adapter.WeatherCastAdapter
import com.bowoon.android.jetpack.ui.vm.MainVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            viewModel.weatherInfoList.collect {
                when (it) {
                    is Status.Loading -> {
                        Log.d("data loading...")
                    }
                    is Status.Success<*> -> {
                        binding.pbLoading.isVisible = false
                        binding.rvWeatherInfoList.apply {
                            @Suppress("UNCHECKED_CAST")
                            adapter = WeatherCastAdapter(it.data as List<WeatherInfo>)
                        }
                    }
                    is Status.Failure -> {
                        Log.e(it.message)
                    }
                }
            }
        }
    }
}