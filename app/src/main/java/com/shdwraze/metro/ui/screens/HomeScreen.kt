package com.shdwraze.metro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shdwraze.metro.R
import com.shdwraze.metro.network.Station
import com.shdwraze.metro.ui.theme.MetroTheme

@Composable
fun HomeScreen(
    metroUiState: MetroUiState,
    modifier: Modifier = Modifier
) {
    when (metroUiState) {
        is MetroUiState.Success -> StationsListScreen(metroUiState.stations, modifier)
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

@Composable
fun LoadingScreen(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading),
        modifier = modifier.size(200.dp)
    )
}

@Composable
fun StationsListScreen(
    stations: List<Station>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = stations, key = { station -> station.id }) { station ->
            StationCard(
                station = station,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun StationCard(station: Station, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(575.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = station.name,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
        )

        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
        ) {
            Text(text = station.city)
            Spacer(modifier = Modifier.width(32.dp))
            Text(text = station.line)
        }

        Text(
            text = if (station.transferTo != null) {
                "Перехід на станцію ${station.transferTo}"
            } else {
                "Немає пересадки"
            },
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 16.dp)
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
            )
        )
    }
}