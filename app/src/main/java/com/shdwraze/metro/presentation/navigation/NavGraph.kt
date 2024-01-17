package com.shdwraze.metro.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shdwraze.metro.presentation.ui.screens.common.TestScreen
import com.shdwraze.metro.presentation.ui.screens.metro.MetroScreen
import com.shdwraze.metro.presentation.ui.screens.station.StationScreen
import com.shdwraze.metro.presentation.ui.screens.welcome.CitySelectorScreen
import com.shdwraze.metro.presentation.ui.screens.welcome.LanguageSelectorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val currentScreen = backStackEntry?.destination?.route ?: Route.MetroScreen.route

    NavHost(
        navController = navController,
        startDestination = Route.TestScreen.route,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable(route = Route.MetroScreen.route) {
            MetroScreen(
                onStationClick = { stationId ->
                    navController.navigate("${Route.StationScreen.route}$stationId")
                },
                scrollBehavior = scrollBehavior,
                canNavigateBack = navController.previousBackStackEntry != null,
                isActionsActive = currentScreen == Route.MetroScreen.route,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = Route.StationScreen.route + STATION_SCREEN_PATH,
            arguments = listOf(navArgument("stationId") {
                type = NavType.StringType
            })
        ) {
            backStackEntry?.arguments?.getString("stationId")?.let { stationId ->
                StationScreen(
                    stationId = stationId,
                    scrollBehavior = scrollBehavior,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    isActionsActive = currentScreen == Route.MetroScreen.route,
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
        }

        composable(
            route = Route.LanguageSelectorScreen.route
        ) {
            LanguageSelectorScreen()
        }

        composable(
            route = Route.CitySelectorScreen.route
        ) {
            CitySelectorScreen()
        }

        composable(
            route = Route.TestScreen.route
        ) {
            TestScreen()
        }
    }

}