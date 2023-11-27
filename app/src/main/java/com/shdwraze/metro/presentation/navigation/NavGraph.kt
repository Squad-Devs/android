package com.shdwraze.metro.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shdwraze.metro.presentation.ui.components.common.MetroTopAppBar
import com.shdwraze.metro.presentation.ui.screens.metro.MetroScreen
import com.shdwraze.metro.presentation.ui.screens.metro.MetroViewModel
import com.shdwraze.metro.presentation.ui.screens.station.StationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val metroViewModel: MetroViewModel = hiltViewModel()

    val currentScreen = backStackEntry?.destination?.route ?: Route.MetroScreen.route

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MetroTopAppBar(
                    scrollBehavior = scrollBehavior,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = {
                        navController.navigateUp()
                    },
                    isActionsActive = currentScreen == Route.MetroScreen.route,
                    lines = metroViewModel.lines
                )
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Route.MetroScreen.route
                ) {
                    composable(route = Route.MetroScreen.route) {
                        MetroScreen(
                            metroUiState = metroViewModel.metroUiState,
                            onStationClick = { station ->
                                metroViewModel.setCurrentStation(station)
                                navController.navigate(Route.StationScreen.route)
                            }
                        )
                    }

                    composable(
                        route = Route.StationScreen.route,
                        arguments = listOf(navArgument("stationId") {
                            type = NavType.StringType
                        })
                    ) {
                        StationScreen(station = metroViewModel.currentStation.value!!,
                            onButtonClick = { stationId ->
                                metroViewModel.findStationById(stationId)
                            })
                    }
                }
            }
        }
    }
}