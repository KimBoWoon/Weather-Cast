package com.bowoon.android.compose.ui.util

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bowoon.android.compose.ui.weather.WeatherItem
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
    val modifier = Modifier.padding()

    WeatherItem(modifier, weatherInfo)
}

@Composable
fun dpToSp(value: Dp) = with(LocalDensity.current) { value.toSp() }