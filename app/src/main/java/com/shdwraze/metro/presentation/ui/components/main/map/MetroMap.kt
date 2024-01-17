package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.presentation.ui.theme.MetroTheme
import com.shdwraze.metro.presentation.ui.utils.MapStyle

@Composable
fun MetroMap(
    metroLines: List<Pair<Color, List<LatLng>>> = listOf()
) {
    val kharkiv = LatLng(49.9935, 36.2304)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            kharkiv,
            13f
        )
    }
    val bounds = LatLngBounds(
        LatLng(49.9044, 36.1672),
        LatLng(50.0766, 36.4484)
    )

    Box(modifier = Modifier.size(400.dp)) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapStyleOptions = MapStyleOptions(MapStyle.json),
                latLngBoundsForCameraTarget = bounds,
                minZoomPreference = 11f,
                maxZoomPreference = 15f
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = true
            ),
        ) {
            metroLines.forEach { metroLine ->
                val (color, coordinates) = metroLine

                Polyline(
                    points = coordinates,
                    color = color,
                    width = 6f,
                    geodesic = true
                )
            }
        }
    }
}

@Preview
@Composable
fun MetroMapPreview() {
    MetroTheme {
        MetroMap()
    }
}