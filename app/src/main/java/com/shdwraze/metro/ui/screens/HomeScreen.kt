package com.shdwraze.metro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shdwraze.metro.R
import com.shdwraze.metro.network.Station
import com.shdwraze.metro.ui.components.StationCard
import com.shdwraze.metro.ui.components.StationsList
import com.shdwraze.metro.ui.theme.MetroTheme

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

@Composable
fun ErrorScreen(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
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