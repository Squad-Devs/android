package com.shdwraze.metro.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shdwraze.metro.network.Station

@Composable
fun StationsList(
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
                    .fillMaxWidth(),
                transferToStationName = station.transferTo?.name
            )
        }
    }
}