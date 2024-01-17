package com.shdwraze.metro.presentation.ui.screens.common

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.model.LatLng
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.presentation.ui.components.main.map.MetroMap
import com.shdwraze.metro.presentation.ui.screens.metro.MetroViewModel
import kotlin.random.Random

@Composable
fun TestScreen(
    metroViewModel: MetroViewModel = hiltViewModel()
) {
    val metroUiState by metroViewModel.metroUiState.collectAsStateWithLifecycle()

    val metroLines = getMetroLinesWithCoordinates(metroUiState.stations)

    Box(modifier = Modifier.fillMaxSize()) {
        Log.d("TEST", metroLines.toString())
        MetroMap(metroLines)
    }
}

/* TODO
    Це повинно бути реалізовано на стороні серверу в майбутньому
 */
private fun getMetroLinesWithCoordinates(stations: List<Station>): List<Pair<Color, List<LatLng>>> {
    val metroLineColors = mapOf(
        "Холодногірсько-заводська лінія" to Color(0xFFEA4335),
        "Салтівська лінія" to Color(0xFF0167b4),
        "Олексіївська лінія" to Color(0xFF00933C)
    )

    val groupedStations = stations.groupBy { it.line }

    return groupedStations.map { (line, stations) ->
        val color = metroLineColors[line] ?: Color.Black

        // Сортуємо станції у кожній лінії
        val sortedStations = sortStations(stations)

        // Перетворимо кожну станцію в LatLng
        val latLngs = sortedStations.map { LatLng(it.latitude.toDouble(), it.longitude.toDouble()) }

        Pair(color, latLngs)
    }
}

private fun sortStations(stations: List<Station>): List<Station> {
    val sortedStations = mutableListOf<Station>()

    // Знайдемо станцію, яка не має попередньої станції - це буде початковою станцією
    var currentStation = stations.find { it.prevStation == null }

    while (currentStation != null) {
        // Додаємо поточну станцію у відсортований список
        sortedStations.add(currentStation)

        // Шукаємо наступну станцію
        currentStation = stations.find { it.id == currentStation?.nextStation?.id }
    }

    return sortedStations
}
