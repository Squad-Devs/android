package com.shdwraze.metro.presentation.ui.screens.station

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shdwraze.metro.data.model.ShortStationInfo
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.presentation.ui.components.station.StationDetailsLine
import com.shdwraze.metro.presentation.ui.components.station.StationDetailsTitle
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

@Composable
fun StationScreen(
    station: Station, onButtonClick: (String) -> Unit = {}
) {
    val nextStation = station.nextStation
    val prevStation = station.prevStation
    val transferTo = station.transferTo

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StationDetailsTitle(name = station.name)
            StationDetailsLine(line = station.line)
            if (transferTo != null) {
                Spacer(modifier = Modifier.size(8.dp))
                TextButton(onClick = { transferTo.let { onButtonClick(it.id) } }) {
                    Text(text = "Перехід на станцію ${transferTo.name}")
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Button(enabled = prevStation != null,
                    onClick = { prevStation?.let { onButtonClick(it.id) } }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
                Button(enabled = nextStation != null,
                    onClick = { nextStation?.let { onButtonClick(it.id) } }) {
                    Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MetroTheme {
        StationScreen(
            station = Station(
                id = "0elwTKJYRQnxhX40pLkG",
                name = "Південний вокзал",
                line = "Холодногірсько-заводська лінія",
                city = "Харків",
                nextStation = ShortStationInfo(
                    id = "gzWqyTLt5AGBCbvJWj6T", name = "Центральний ринок"
                ),
                prevStation = ShortStationInfo(
                    id = "Vv0QmA8fYHpNqI0rgR4S", name = "Холодна гора"
                ),
                transferTo = ShortStationInfo(
                    id = "Vv0QmA8fYHpNqI0rgR4S", name = "Холодна гора"
                )
            )
        )
    }
}