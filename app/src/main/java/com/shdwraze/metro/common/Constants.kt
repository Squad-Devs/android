package com.shdwraze.metro.common

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

object Constants {

    const val DEFAULT_CITY = "Харків"

    const val SERVER_URL = "https://backend-service-jeit.onrender.com"

    const val DELAY_FOR_USE_CASE = 0L

    // CITY MAPS CONSTANTS

    val KHARKIV_FOCUS = LatLng(49.9935, 36.2304)

    val KHARKIV_BOUNDS = LatLngBounds(
        LatLng(49.943821752971196, 36.184248679054896),
        LatLng(50.05967635718973, 36.40225863088736)
    )

    // COMMON MAPS CONSTANTS

    const val DEFAULT_ZOOM = 13f

    const val MIN_ZOOM = 12f

    const val MAX_ZOOM = 14f

    // METRO LINE COLORS

    const val RED_LINE_COLOR = -1424587

    const val BLUE_LINE_COLOR = -16685132

    const val GREEN_LINE_COLOR = -16739524
}