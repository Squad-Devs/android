package com.shdwraze.metro.presentation.ui.components.metro.station

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shdwraze.metro.network.Station
import com.shdwraze.metro.presentation.utils.Dimens.SmallPadding4

@Composable
fun StationsList(
    stations: List<Station>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(SmallPadding4)
    ) {
        items(items = stations, key = { station -> station.id }) { station ->
            StationCard(
                station = station,
                modifier = modifier
                    .padding(SmallPadding4)
                    .fillMaxWidth(),
                transferToStationName = station.transferTo?.name
            )
        }
    }
}