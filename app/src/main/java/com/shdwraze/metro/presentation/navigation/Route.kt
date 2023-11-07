package com.shdwraze.metro.presentation.navigation

sealed class Route(
    val route: String
) {
    object MetroScreen : Route(route = "metroScreen")

    object StationScreen : Route(route = "stationScreen")

    object AppStartNavigation: Route(route = "appStartNavigation")
}