package com.shdwraze.metro.presentation.ui.screens.metro

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.presentation.ui.components.common.MetroTopAppBar
import com.shdwraze.metro.presentation.ui.components.metro.station.StationCard
import com.shdwraze.metro.presentation.ui.components.metro.station.StationsList
import com.shdwraze.metro.presentation.ui.screens.common.error.ErrorScreen
import com.shdwraze.metro.presentation.ui.screens.common.loading.LoadingScreen
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetroScreen(
    metroViewModel: MetroViewModel = hiltViewModel(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onStationClick: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    canNavigateBack: Boolean = true,
    navigateUp: () -> Unit = {},
    isActionsActive: Boolean = true
) {
    val metroUiState by metroViewModel.metroUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MetroTopAppBar(
                scrollBehavior = scrollBehavior,
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                isActionsActive = isActionsActive,
                lines = metroViewModel.lines,
                onDropdownItemClick = { line ->
                    metroViewModel.getStations(line)
                }
            )
        }
    ) { innerPadding ->
        when {
            metroUiState.isLoading -> {
                LoadingScreen(modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding))
            }

            metroUiState.isError -> {
                ErrorScreen(modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding))
            }

            else -> {
                StationsList(
                    metroUiState.stations,
                    modifier.padding(innerPadding),
                    onStationClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MetroTheme {
        StationCard(
            station = Station(
                "Dsadsad",
                "Держпром",
                "Олексіївська лінія",
                "Харків",
                null,
                null,
                null
            ),
            transferToStationName = null,
            onStationClick = {}
        )
    }
}