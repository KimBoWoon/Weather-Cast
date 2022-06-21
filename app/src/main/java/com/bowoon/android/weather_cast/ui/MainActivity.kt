package com.bowoon.android.weather_cast.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.R
import com.bowoon.android.weather_cast.base.BaseActivity
import com.bowoon.android.weather_cast.databinding.ActivityMainBinding
import com.bowoon.android.weather_cast.ui.vm.MainVM
import com.bowoon.android.weather_cast.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    private val viewModel by viewModels<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    WeatherCastCompose()
                }
            }
        }

//        binding.apply {
//            lifecycleOwner = this@MainActivity
//        }
//        lifecycle.addObserver(viewModel)
//
//        initBinding()
//        initLiveData()
    }

    override fun initBinding() {
//        binding.apply {
//            rvWeatherInfoList.apply {
//                if (itemDecorationCount == 0) {
//                    addItemDecoration(WeatherCastAdapter.WeatherListDecoration())
//                }
//            }
//        }
    }

    override fun initLiveData() {
//        viewModel.weatherInfoList.observe(this) { weatherInfoList ->
//            binding.pbLoading.isVisible = false
//            binding.rvWeatherInfoList.apply {
//                adapter = WeatherCastAdapter(weatherInfoList)
//            }
//        }
    }
}

@Composable
fun WeatherCastCompose(viewModel: MainVM = viewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { WeatherCastActionBar(snackbarHostState) },
        content = {
            val status by viewModel.status.collectAsState()

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
                            fontSize = 20.sp,
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
        title = { Text(text = "날씨", color = Color.White) },
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
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 10.dp)
                )
                Spacer(modifier = Modifier.padding(end = 5.dp))
                Text(
                    text = "${weatherInfo.main?.temp?.toString() ?: NONE}°C",
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            weatherInfo.weather?.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "${it?.main ?: NONE}(${it?.description ?: NONE})")
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
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "풍속 : ${weatherInfo.wind?.speed}\n풍향 : ${getDegree(weatherInfo.wind?.deg ?: 0)}\n돌풍 : ${weatherInfo.wind?.gust}",
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Preview(
    name = "preview",
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewWeatherItem() {
    val weatherInfo = WeatherInfo()

    WeatherItem(0, 0, weatherInfo)
}