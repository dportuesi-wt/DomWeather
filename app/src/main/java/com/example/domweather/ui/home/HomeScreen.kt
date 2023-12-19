package com.example.domweather.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domweather.ui.components.WeatherCard

@Composable
fun HomeScreenRoute(
    homeViewModel: HomeViewModel
) {
    val currentLocalTemperature = homeViewModel.currentLocalTemp.collectAsState(0)
    HomeScreen(
        currentLocalTemperature = currentLocalTemperature.value,
        syncLocalTemp = homeViewModel::syncLocalTemp
    )
}

@Composable
fun HomeScreen(
    currentLocalTemperature: Int,
    syncLocalTemp: () -> Unit
) {
    Column {
        WeatherCard(
            temperature = currentLocalTemperature,
            onSyncClicked = syncLocalTemp,
            modifier = Modifier
                .padding(8.dp)
        )
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    HomeScreen(
        currentLocalTemperature = 46,
        syncLocalTemp = {}
    )
}