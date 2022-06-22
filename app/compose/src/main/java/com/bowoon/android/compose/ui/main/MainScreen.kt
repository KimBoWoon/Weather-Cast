package com.bowoon.android.compose.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bowoon.android.compose.ui.util.dpToSp
import com.bowoon.android.compose.ui.vm.MainVM
import com.bowoon.android.compose.util.Log
import com.bowoon.android.compose.util.Status
import com.bowoon.android.domain.dto.WeatherInfo
import kotlinx.coroutines.launch

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