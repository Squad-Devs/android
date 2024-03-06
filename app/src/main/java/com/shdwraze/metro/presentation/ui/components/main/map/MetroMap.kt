package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.shdwraze.metro.R
import com.shdwraze.metro.common.Constants.DEFAULT_ZOOM
import com.shdwraze.metro.common.Constants.KHARKIV_BOUNDS
import com.shdwraze.metro.common.Constants.KHARKIV_FOCUS
import com.shdwraze.metro.common.Constants.MAX_ZOOM
import com.shdwraze.metro.common.Constants.MIN_ZOOM
import com.shdwraze.metro.data.model.ConnectionType
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.presentation.ui.components.common.MapMarker
import com.shdwraze.metro.presentation.ui.theme.MetroTheme
import com.shdwraze.metro.presentation.ui.utils.IconColorPicker.getMarkerIconResource
import com.shdwraze.metro.presentation.ui.utils.IconColorPicker.getTransferIconResource
import com.shdwraze.metro.presentation.ui.utils.MapStyle

const val POLYLINE_WIDTH = 14f

@Composable
fun MetroMap(
    metropolitan: Metropolitan = Metropolitan()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            KHARKIV_FOCUS,
            DEFAULT_ZOOM
        )
    }

    Box(modifier = Modifier.size(400.dp)) {
        CustomGoogleMap(cameraPositionState, metropolitan)
    }
}

@Preview
@Composable
fun MetroMapPreview() {
    MetroTheme {
        MetroMap()
    }
}