package com.shdwraze.metro.presentation.ui.screens.metro

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.presentation.ui.components.metro.station.StationCard
import com.shdwraze.metro.presentation.ui.components.metro.station.StationsList
import com.shdwraze.metro.presentation.ui.screens.common.error.ErrorScreen
import com.shdwraze.metro.presentation.ui.screens.common.loading.LoadingScreen
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

@Composable
fun MetroScreen(
    metroViewModel: MetroViewModel = hiltViewModel(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onStationClick: (Station) -> Unit
) {
    val metroUiState by metroViewModel.metroUiState.collectAsStateWithLifecycle()

    when {
        metroUiState.isLoading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }
        metroUiState.isError -> {
            ErrorScreen(modifier = modifier.fillMaxSize())
        }
        else -> {
            StationsList(
                metroUiState.stations,
                modifier,
                onStationClick
            )
        }
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
            transferToStationName = null,
            onStationClick = {}
        )
    }
}