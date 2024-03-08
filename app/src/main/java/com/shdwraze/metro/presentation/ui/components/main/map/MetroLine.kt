package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Polyline
import com.shdwraze.metro.data.model.MetroLine
import com.shdwraze.metro.data.model.ShortestPath

@Composable
fun MetroLine(
    metroLine: MetroLine,
    isHaveShortestPath: Boolean,
    shortestPath: ShortestPath
) {
    if (isHaveShortestPath) {
        val shortestPathStations = shortestPath.path

        metroLine.stations.windowed(2).forEach { (station1, station2) ->
            val coordinates = listOf(
                LatLng(station1.latitude, station1.longitude),
                LatLng(station2.latitude, station2.longitude)
            )
            val isInShortestPath =
                shortestPathStations.contains(station1) && shortestPathStations.contains(station2)
            val color =
                if (isInShortestPath) Color(metroLine.color) else Color(metroLine.color).copy(alpha = 0.5f)
            Polyline(
                points = coordinates,
                color = color,
                width = POLYLINE_WIDTH,
                geodesic = true
            )
        }
        metroLine.stations.forEach { station ->
            StationMarker(
                station = station,
                metroLineColor = metroLine.color,
                isHaveShortestPath = true,
                isInShortestPath = shortestPathStations.contains(station)
            )
        }
    } else {
        val coordinates = metroLine.stations.map { LatLng(it.latitude, it.longitude) }

        Polyline(
            points = coordinates,
            color = Color(metroLine.color),
            width = POLYLINE_WIDTH,
            geodesic = true
        )
        metroLine.stations.forEach { station ->
            StationMarker(
                station = station,
                metroLineColor = metroLine.color,
                isHaveShortestPath = false,
                isInShortestPath = false
            )
        }
    }
}