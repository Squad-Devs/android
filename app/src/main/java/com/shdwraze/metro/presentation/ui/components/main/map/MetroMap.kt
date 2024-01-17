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
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.shdwraze.metro.common.Constants.DEFAULT_ZOOM
import com.shdwraze.metro.common.Constants.KHARKIV_BOUNDS
import com.shdwraze.metro.common.Constants.KHARKIV_FOCUS
import com.shdwraze.metro.common.Constants.MAX_ZOOM
import com.shdwraze.metro.common.Constants.MIN_ZOOM
import com.shdwraze.metro.presentation.ui.theme.MetroTheme
import com.shdwraze.metro.presentation.ui.utils.MapStyle

const val POLYLINE_WIDTH = 6f

@Composable
fun MetroMap(
    metroLines: List<Pair<Color, List<LatLng>>> = listOf()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            KHARKIV_FOCUS,
            DEFAULT_ZOOM
        )
    }

    Box(modifier = Modifier.size(400.dp)) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapStyleOptions = MapStyleOptions(MapStyle.json),
                latLngBoundsForCameraTarget = KHARKIV_BOUNDS,
                minZoomPreference = MIN_ZOOM,
                maxZoomPreference = MAX_ZOOM
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
                    width = POLYLINE_WIDTH,
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