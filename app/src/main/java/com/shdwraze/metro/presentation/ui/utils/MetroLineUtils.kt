package com.shdwraze.metro.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng
import com.shdwraze.metro.data.model.Station

object MetroLineUtils {
    // TODO strings.xml
    private val metroLineColors = mapOf(
        "Холодногірсько-заводська лінія" to Color(0xFFEA4335),
        "Салтівська лінія" to Color(0xFF0167b4),
        "Олексіївська лінія" to Color(0xFF00933C)
    )

    fun getMetroLinesWithCoordinates(stations: List<Station>): List<Pair<Color, List<LatLng>>> {
        val groupedStations = stations.groupBy { it.line }

        return groupedStations.map { (line, stations) ->
            val color = metroLineColors[line] ?: Color.Black

            val sortedStations = sortStations(stations)

            val latLngs = sortedStations.map { LatLng(it.latitude.toDouble(), it.longitude.toDouble()) }

            Pair(color, latLngs)
        }
    }

    private fun sortStations(stations: List<Station>): List<Station> {
        val sortedStations = mutableListOf<Station>()

        var currentStation = stations.find { it.prevStation == null }

        while (currentStation != null) {
            sortedStations.add(currentStation)

            currentStation = stations.find { it.id == currentStation?.nextStation?.id }
        }

        return sortedStations
    }
}
