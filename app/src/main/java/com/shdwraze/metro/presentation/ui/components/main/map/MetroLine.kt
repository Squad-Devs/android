package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Polyline
import com.shdwraze.metro.data.model.MetroLine

@Composable
fun MetroLine(metroLine: MetroLine) {
    val coordinates = metroLine.stations.map { LatLng(it.latitude, it.longitude) }
    Polyline(
        points = coordinates,
        color = Color(metroLine.color),
        width = POLYLINE_WIDTH,
        geodesic = true
    )
    metroLine.stations.forEach { station ->
        StationMarker(station, metroLine.color)
    }
}