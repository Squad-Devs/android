package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState
import com.shdwraze.metro.common.Constants.DEFAULT_ZOOM
import com.shdwraze.metro.common.Constants.KHARKIV_FOCUS
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.model.ShortestPath
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

const val POLYLINE_WIDTH = 14f

@Composable
fun MetroMap(
    metropolitan: Metropolitan = Metropolitan(),
    shortestPath: ShortestPath = ShortestPath()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            KHARKIV_FOCUS,
            DEFAULT_ZOOM
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxSize()) {
            CustomGoogleMap(
                cameraPositionState = cameraPositionState,
                metropolitan = metropolitan,
                shortestPath = shortestPath
            )
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