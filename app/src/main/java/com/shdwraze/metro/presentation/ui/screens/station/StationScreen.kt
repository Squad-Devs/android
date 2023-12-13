package com.shdwraze.metro.presentation.ui.screens.station

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shdwraze.metro.presentation.ui.components.common.MetroTopAppBar
import com.shdwraze.metro.presentation.ui.components.station.StationDetailsLine
import com.shdwraze.metro.presentation.ui.components.station.StationDetailsTitle
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationScreen(
    stationViewModel: StationViewModel = hiltViewModel(),
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    canNavigateBack: Boolean = true,
    navigateUp: () -> Unit = {},
    isActionsActive: Boolean = false
) {
    val stationUiState by stationViewModel.stationUiState.collectAsStateWithLifecycle()

    val station = stationUiState.station
    Log.d("DEBUG", "STATION: $station")
    val nextStation = station.nextStation
    val prevStation = station.prevStation
    val transferTo = station.transferTo

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MetroTopAppBar(
                scrollBehavior = scrollBehavior,
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                isActionsActive = isActionsActive
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StationDetailsTitle(name = station.name)
                StationDetailsLine(line = station.line)
                if (transferTo != null) {
                    Spacer(modifier = Modifier.size(8.dp))
                    TextButton(onClick = {
                        transferTo.let {
                            stationViewModel.getStationById(it.id)
                        }
                    }) {
                        Text(text = "Перехід на станцію ${transferTo.name}")
                    }
                } else {
                    Spacer(modifier = Modifier.size(48.dp))
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    Button(enabled = prevStation != null,
                        onClick = { prevStation?.let { stationViewModel.getStationById(it.id) } }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                    Button(enabled = nextStation != null,
                        onClick = { nextStation?.let { stationViewModel.getStationById(it.id) } }) {
                        Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview() {
    MetroTheme {
        StationScreen()
    }
}