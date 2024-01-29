package com.shdwraze.metro.presentation.ui.components.common

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberMarkerState
import com.shdwraze.metro.R
import com.shdwraze.metro.presentation.ui.utils.BitmapDescriptor.bitmapDescriptorFromVector

@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    stationName: String = "",
    line: String = "",
    hasTransferStation: Boolean = false,
    transferToStationName: String = "",
    @DrawableRes markerIconResourceId: Int = R.drawable.marker_red,
    @DrawableRes transferIconResourceId: Int = R.drawable.transfer_marker_blue_green,
) {
    val icon = bitmapDescriptorFromVector(
        context, markerIconResourceId
    )
    MarkerInfoWindow(
        state = rememberMarkerState(position = position),
        icon = icon,
        anchor = Offset(0.5f, 0.5f)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color.White)
                .defaultMinSize(275.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stationName,
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )
                Text(
                    text = line,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF9C9C9C),
                    )
                )
                if (hasTransferStation) {
                    Spacer(modifier = Modifier.size(16.dp))
                    Row {
                        Icon(
                            painter = painterResource(transferIconResourceId),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = transferToStationName,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                }
            }

        }
    }
}