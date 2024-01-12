package com.shdwraze.metro.presentation.navigation

const val STATION_SCREEN_PATH = "{stationId}"

sealed class Route(
    val route: String
) {

    object MetroScreen : Route(route = "metroScreen")

    object StationScreen : Route(route = "stationScreen/")

    object AppStartNavigation: Route(route = "appStartNavigation")

    object TestScreen : Route(route = "testScreen")

    fun getRouteByName(name: String): Route? {
        return when (name) {
            "metroScreen" -> MetroScreen
            "stationScreen" -> StationScreen
            "appStartNavigation" -> AppStartNavigation
            "testScreen" -> TestScreen
            else -> null
        }
    }

}