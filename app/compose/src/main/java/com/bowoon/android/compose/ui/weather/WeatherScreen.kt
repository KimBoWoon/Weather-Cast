package com.bowoon.android.compose.ui.weather

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bowoon.android.compose.ui.util.dpToSp
import com.bowoon.android.compose.util.NONE
import com.bowoon.android.compose.util.getDegree
import com.bowoon.android.compose.util.getWeatherColor
import com.bowoon.android.domain.dto.WeatherInfo
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Job

@Composable
fun WeatherItem(
    modifier: Modifier,
    weatherInfo: WeatherInfo,
    clickEvent: (() -> Job)? = null
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ) {
        var isExpanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .background(getWeatherColor(weatherInfo.weather?.firstOrNull()?.id ?: 0))
                .clickable {
                    isExpanded = !isExpanded
                    clickEvent?.invoke()
                }
        ) {
            Row {
                Text(
                    text = weatherInfo.name ?: NONE,
                    color = Color.Black,
                    fontSize = dpToSp(20.dp),
                    modifier = Modifier.padding(start = 16.dp, top = 10.dp)
                )
                Spacer(modifier = Modifier.padding(end = 5.dp))
                Text(
                    text = "${weatherInfo.main?.temp?.toString() ?: NONE}°C",
                    color = Color.Black,
                    fontSize = dpToSp(20.dp),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            weatherInfo.weather?.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${it?.main ?: NONE}(${it?.description ?: NONE})",
                        fontSize = dpToSp(18.dp)
                    )

                    GlideImage(
                        imageModel = it?.getIconUrl(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(30.dp).width(30.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            Surface(modifier = Modifier.animateContentSize()) {
                Column(
                    modifier = Modifier
                        .height(if (isExpanded) Dp.Infinity else 0.dp)
                        .background(getWeatherColor(weatherInfo.weather?.firstOrNull()?.id ?: 0))
                        .padding(start = 16.dp, bottom = 10.dp)
                ) {
                    Text(
                        text = "최고 온도 : ${weatherInfo.main?.tempMax}°C\n최저 온도 : ${weatherInfo.main?.tempMin}°C",
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = dpToSp(15.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    var windText = ""
                    weatherInfo.wind?.speed?.let { windText += "풍속 : $it㎧" }
                    weatherInfo.wind?.deg?.let { windText += "\n풍향 : ${getDegree(it)}" }
                    weatherInfo.wind?.gust?.let { windText += "\n돌풍 : $it㎧" }

                    Text(
                        text = windText,
                        style = MaterialTheme.typography.body2,
                        fontSize = dpToSp(15.dp)
                    )
                }
            }
        }
    }
}

@Preview(
    name = "weather item preview",
    showBackground = true
)
@Composable
fun Preview() {
    WeatherItem(modifier = Modifier, WeatherInfo(), null)
}