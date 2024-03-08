package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.shdwraze.metro.common.Constants.KHARKIV_BOUNDS
import com.shdwraze.metro.common.Constants.MAX_ZOOM
import com.shdwraze.metro.common.Constants.MIN_ZOOM
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.model.ShortestPath
import com.shdwraze.metro.presentation.ui.utils.MapStyle

@Composable
fun CustomGoogleMap(
    cameraPositionState: CameraPositionState,
    metropolitan: Metropolitan,
    shortestPath: ShortestPath
) {
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
        metropolitan.lines.forEach { metroLine ->
            MetroLine(
                metroLine = metroLine,
                isHaveShortestPath = shortestPath != ShortestPath(),
                shortestPath = shortestPath
            )
        }
    }
}