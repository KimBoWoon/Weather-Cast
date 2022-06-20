package com.bowoon.android.weather_cast.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bowoon.android.domain.dto.WeatherInfo
import com.bowoon.android.weather_cast.R
import com.bowoon.android.weather_cast.base.BaseActivity
import com.bowoon.android.weather_cast.databinding.ActivityMainBinding
import com.bowoon.android.weather_cast.ui.vm.MainVM
import com.bowoon.android.weather_cast.util.Log
import com.bowoon.android.weather_cast.util.NONE
import com.bowoon.android.weather_cast.util.getWeatherColor
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
                    WeatherCastCompose(viewModel.weatherInfoList)
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
fun WeatherCastCompose(weatherInfo: List<WeatherInfo?>?) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { WeatherCastActionBar(snackbarHostState) },
        content = { WeatherCastContent(weatherInfo) },
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
        modifier = Modifier.padding(
            start = 8.dp,
            top = if (index == 0) 8.dp else 4.dp,
            end = 8.dp,
            bottom = if (index == lastIndex) 8.dp else 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .background(getWeatherColor(weatherInfo.weather?.firstOrNull()?.id ?: 0))
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
                    text = weatherInfo.main?.temp?.toString() ?: NONE,
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