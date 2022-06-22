package com.bowoon.android.compose.ui.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.bowoon.android.compose.ui.main.WeatherItem
import com.bowoon.android.domain.dto.WeatherInfo

@Preview(
    name = "preview light",
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "preview dark",
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewWeatherItem() {
    val weatherInfo = WeatherInfo()

    WeatherItem(0, 0, weatherInfo)
}

@Composable
fun dpToSp(value: Dp) = with(LocalDensity.current) { value.toSp() }