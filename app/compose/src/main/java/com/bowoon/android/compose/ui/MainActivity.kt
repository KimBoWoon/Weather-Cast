package com.bowoon.android.compose.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bowoon.android.compose.base.BaseActivity
import com.bowoon.android.compose.ui.vm.MainVM
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.compose.util.Log
import com.bowoon.android.compose.util.NONE
import com.bowoon.android.compose.util.Status
import com.bowoon.android.compose.util.getWeatherColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    WeatherCastCompose()
                }
            }
        }
    }
}

@Composable
fun WeatherCastCompose(viewModel: MainVM = viewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { WeatherCastActionBar(snackbarHostState) },
        content = {
            val status by viewModel.weatherInfoStatus.collectAsState()

            when (status) {
                is Status.Loading -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }
                is Status.Success -> {
                    WeatherCastContent(weatherInfo = (status as Status.Success).data)
                }
                is Status.Failure -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = (status as Status.Failure).message,
                            fontSize = dpToSp(20.dp),
                            color = Color.Black
                        )
                    }
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
}

@Composable
fun WeatherCastActionBar(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = "날씨", color = Color.White, fontSize = dpToSp(20.dp)) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    val snackbarResult = snackbarHostState.showSnackbar(
                        "Hello, World!!!",
                        "actionLabel",
                        SnackbarDuration.Short
                    )

                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarResult Dismissed")
                        SnackbarResult.ActionPerformed -> Log.d("SnackbarResult ActionPerformed")
                    }
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Btn")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 2.dp,
        modifier = Modifier
            .wrapContentHeight(Alignment.Top)
            .fillMaxWidth()
    )
}

@Composable
fun WeatherCastContent(weatherInfo: List<WeatherInfo?>?) {
    LazyColumn {
        weatherInfo?.let {
            itemsIndexed(weatherInfo) { index, weather ->
                weather?.let {
                    WeatherItem(index, weatherInfo.lastIndex, weather)
                }
            }
        }
    }
}

@Composable
fun WeatherItem(index: Int, lastIndex: Int, weatherInfo: WeatherInfo) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(
                start = 8.dp,
                top = if (index == 0) 8.dp else 4.dp,
                end = 8.dp,
                bottom = if (index == lastIndex) 8.dp else 4.dp
            )
            .clickable(
                enabled = true,
                onClick = {
                    Log.d("${weatherInfo.name ?: NONE} clicked")
                }
            )
    ) {
        var isExpanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .background(getWeatherColor(weatherInfo.weather?.firstOrNull()?.id ?: 0))
                .clickable { isExpanded = !isExpanded }
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
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            Surface(modifier = Modifier.animateContentSize()) {
                Column(modifier = Modifier
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
                    weatherInfo.wind?.speed?.let { windText += "풍속 : $it"}
                    weatherInfo.wind?.deg?.let { windText += "\n풍향 : $it"}
                    weatherInfo.wind?.gust?.let { windText += "\n돌풍 : $it"}

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