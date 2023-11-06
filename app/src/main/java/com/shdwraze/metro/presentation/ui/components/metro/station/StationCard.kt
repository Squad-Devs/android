package com.shdwraze.metro.presentation.ui.components.metro.station

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shdwraze.metro.network.Station
import com.shdwraze.metro.presentation.utils.Dimens.LargePadding32
import com.shdwraze.metro.presentation.utils.Dimens.MediumPadding16
import com.shdwraze.metro.presentation.utils.Dimens.SmallPadding8

@Composable
fun StationCard(station: Station, modifier: Modifier = Modifier, transferToStationName: String?) {
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
                .padding(start = MediumPadding16, top = MediumPadding16)
        )

        Row(
            modifier = Modifier
                .padding(start = MediumPadding16, top = SmallPadding8)
        ) {
            Text(text = station.city)
            Spacer(modifier = Modifier.width(LargePadding32))
            Text(text = station.line)
        }

        Text(
            text = if (station.transferTo != null) {
                "Перехід на станцію $transferToStationName"
            } else {
                "Немає пересадки"
            },
            modifier = Modifier
                .padding(start = MediumPadding16, top = SmallPadding8, bottom = MediumPadding16)
        )
    }
}