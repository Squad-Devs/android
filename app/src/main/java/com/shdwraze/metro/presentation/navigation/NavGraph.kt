package com.shdwraze.metro.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shdwraze.metro.presentation.ui.components.common.MetroTopAppBar
import com.shdwraze.metro.presentation.ui.screens.metro.MetroScreen
import com.shdwraze.metro.presentation.ui.screens.metro.MetroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { MetroTopAppBar(scrollBehavior = scrollBehavior) }
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
                        val metroViewModel: MetroViewModel = hiltViewModel()
                        MetroScreen(metroUiState = metroViewModel.metroUiState)
                    }
                }
            }
        }
    }
}