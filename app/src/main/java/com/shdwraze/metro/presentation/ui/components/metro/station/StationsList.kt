package com.shdwraze.metro.presentation.ui.components.metro.station

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shdwraze.metro.common.utils.Dimens.SmallPadding4
import com.shdwraze.metro.data.model.Station

@Composable
fun StationsList(
    stations: List<Station>,
    modifier: Modifier = Modifier,
    onStationClick: (Station) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(SmallPadding4)
    ) {
        items(items = stations, key = { station -> station.id }) { station ->
            StationCard(
                station = station,
                modifier = Modifier
                    .padding(SmallPadding4)
                    .fillMaxWidth(),
                transferToStationName = station.transferTo?.name,
                onStationClick = onStationClick
            )
        }
    }
}