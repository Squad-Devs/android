package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.LatLng
import com.shdwraze.metro.R
import com.shdwraze.metro.data.model.ConnectionType
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.presentation.ui.components.common.MapMarker
import com.shdwraze.metro.presentation.ui.utils.IconColorPicker.getMarkerIconResource
import com.shdwraze.metro.presentation.ui.utils.IconColorPicker.getTransferIconResource

@Composable
fun StationMarker(
    station: Station,
    metroLineColor: Int,
    isHaveShortestPath: Boolean,
    isInShortestPath: Boolean
) {
    if (!isHaveShortestPath || isInShortestPath) {
        val connections = station.connections
        val connectedTransferStation = connections.find {
            it.type == ConnectionType.TRANSFER.name
        }
        val transferToStationColor = connectedTransferStation?.toStation?.line?.color

        MapMarker(
            context = LocalContext.current,
            position = LatLng(station.latitude, station.longitude),
            stationName = station.name,
            line = station.line.name,
            hasTransferStation = connectedTransferStation != null,
            transferToStationName = connectedTransferStation?.toStation?.name ?: "",
            markerIconResourceId = getMarkerIconResource(metroLineColor),
            transferIconResourceId = transferToStationColor?.let {
                getTransferIconResource(
                    metroLineColor, it
                )
            }
                ?: R.drawable.transfer_marker_red_blue
        )
    }
}
