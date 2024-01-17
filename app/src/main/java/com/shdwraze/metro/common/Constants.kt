package com.shdwraze.metro.common

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

object Constants {

    const val DEFAULT_CITY = "Харків"

    const val SERVER_URL = "https://metro-backend.fly.dev"

    const val DELAY_FOR_USE_CASE = 0L

    // CITY MAPS CONSTANTS

    val KHARKIV_FOCUS = LatLng(49.9935, 36.2304)

    val KHARKIV_BOUNDS = LatLngBounds(
        LatLng(49.9044, 36.1672),
        LatLng(50.0766, 36.4484)
    )

    // COMMON MAPS CONSTANTS

    const val DEFAULT_ZOOM = 13f

    const val MIN_ZOOM = 11f

    const val MAX_ZOOM = 15f
}