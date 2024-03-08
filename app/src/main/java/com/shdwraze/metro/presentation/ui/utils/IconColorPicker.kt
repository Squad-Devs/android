package com.shdwraze.metro.presentation.ui.utils

import com.shdwraze.metro.R
import com.shdwraze.metro.common.Constants.BLUE_LINE_COLOR
import com.shdwraze.metro.common.Constants.GREEN_LINE_COLOR
import com.shdwraze.metro.common.Constants.RED_LINE_COLOR

object IconColorPicker {
    private val transferIcons = mapOf(
        Pair(RED_LINE_COLOR, BLUE_LINE_COLOR) to R.drawable.transfer_marker_red_blue,
        Pair(RED_LINE_COLOR, GREEN_LINE_COLOR) to R.drawable.transfer_marker_red_green,
        Pair(GREEN_LINE_COLOR, BLUE_LINE_COLOR) to R.drawable.transfer_marker_green_blue,
        Pair(BLUE_LINE_COLOR, RED_LINE_COLOR) to R.drawable.transfer_marker_blue_red,
        Pair(GREEN_LINE_COLOR, RED_LINE_COLOR) to R.drawable.transfer_marker_green_red,
        Pair(BLUE_LINE_COLOR, GREEN_LINE_COLOR) to R.drawable.transfer_marker_blue_green,
    )

    fun getTransferIconResource(metroLineColor: Int, transferToStationColor: Int): Int {
        return transferIcons[Pair(metroLineColor, transferToStationColor)]
            ?: R.drawable.transfer_marker_red_blue
    }

    fun getMarkerIconResource(metroLineColor: Int): Int {
        return when (metroLineColor) {
            RED_LINE_COLOR -> R.drawable.marker_red
            BLUE_LINE_COLOR -> R.drawable.marker_blue
            else -> R.drawable.marker_green
        }
    }
}
