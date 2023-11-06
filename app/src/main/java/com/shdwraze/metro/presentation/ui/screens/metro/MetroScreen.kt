package com.shdwraze.metro.presentation.ui.screens.metro

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shdwraze.metro.network.Station
import com.shdwraze.metro.presentation.ui.components.metro.station.StationCard
import com.shdwraze.metro.presentation.ui.components.metro.station.StationsList
import com.shdwraze.metro.presentation.ui.screens.common.error.ErrorScreen
import com.shdwraze.metro.presentation.ui.screens.common.loading.LoadingScreen
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

@Composable
fun HomeScreen(
    metroUiState: MetroUiState,
    modifier: Modifier = Modifier
) {
    when (metroUiState) {
        is MetroUiState.Success -> StationsList(metroUiState.stations, modifier)
        is MetroUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        else -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MetroTheme {
        StationCard(
            station = Station(
                "Dsadsad",
                "Держпром",
                "Олексіївська лінія",
                "Харків",
                null,
                null,
                null
            ),
            transferToStationName = null
        )
    }
}