package com.bowoon.android.compose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.bowoon.android.compose.base.BaseActivity
import com.bowoon.android.compose.ui.main.WeatherCastCompose
import com.bowoon.android.compose.ui.theme.WeatherCastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherCastTheme {
                Surface {
                    WeatherCastCompose()
                }
            }
        }
    }
}