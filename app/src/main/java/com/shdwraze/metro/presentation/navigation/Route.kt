package com.shdwraze.metro.presentation.navigation

sealed class Route(
    val route: String
) {
    object MetroScreen : Route(route = "metroScreen")

    object StationScreen : Route(route = "stationScreen/{stationId}")

    object AppStartNavigation: Route(route = "appStartNavigation")

    fun getRouteByName(name: String): Route? {
        return when (name) {
            "metroScreen" -> MetroScreen
            "stationScreen" -> StationScreen
            "appStartNavigation" -> AppStartNavigation
            else -> null
        }
    }

}