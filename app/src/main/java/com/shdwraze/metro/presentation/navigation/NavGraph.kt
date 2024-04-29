package com.shdwraze.metro.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shdwraze.metro.presentation.ui.screens.common.MainScreen
import com.shdwraze.metro.presentation.ui.screens.welcome.CitySelectorScreen
import com.shdwraze.metro.presentation.ui.screens.welcome.LanguageSelectorScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.MainScreen.route,
        modifier = Modifier
            .fillMaxSize()
    ) {
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
            route = Route.MainScreen.route
        ) {
            MainScreen()
        }
    }
}