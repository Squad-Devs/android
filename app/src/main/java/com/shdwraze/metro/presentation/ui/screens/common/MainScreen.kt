package com.shdwraze.metro.presentation.ui.screens.common

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shdwraze.metro.presentation.ui.components.common.CustomTopAppBar
import com.shdwraze.metro.presentation.ui.components.main.RouteInfo
import com.shdwraze.metro.presentation.ui.components.main.bottomsheetcontent.BottomSheetContent
import com.shdwraze.metro.presentation.ui.components.main.map.MetroMap
import com.shdwraze.metro.presentation.ui.components.main.sidebar.SidebarMenu
import com.shdwraze.metro.presentation.ui.screens.metro.MetroViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    metroViewModel: MetroViewModel = hiltViewModel()
) {
    val metroUiState by metroViewModel.metroUiState.collectAsStateWithLifecycle()
    val startStationQueryValue by metroViewModel.startStationQueryValue.collectAsStateWithLifecycle()
    val endStationQueryValue by metroViewModel.endStationQueryValue.collectAsStateWithLifecycle()

    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = false
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            SidebarMenu(onCloseButtonClick = {
                scope.launch {
                    drawerState.close()
                }
            })
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ) {
        BottomSheetScaffold(
            sheetContent = {
                BottomSheetContent(
                    onCalculateButtonClick = { startStation, endStation ->
                        metroViewModel.getShortestPath(startStation, endStation)
                        scope.launch {
                            sheetState.hide()
                        }
                    },
                    onResetButtonClick = {
                        metroViewModel.resetShortestPath()
                        metroViewModel.updateStartStationQueryValue(TextFieldValue(""))
                        metroViewModel.updateEndStationQueryValue(TextFieldValue(""))
                    },
                    stationsMap = metroViewModel.stationsMap,
                    startStationQueryValue = startStationQueryValue,
                    endStationQueryValue = endStationQueryValue,
                    onStartStationQueryValueChange = metroViewModel::updateStartStationQueryValue,
                    onEndStationQueryValueChange = metroViewModel::updateEndStationQueryValue,
                    onActionStart = {
                        scope.launch { sheetState.expand() }
                    }
                )
            },
            sheetPeekHeight = 154.dp,
            sheetShadowElevation = 16.dp,
            sheetContainerColor = MaterialTheme.colorScheme.onPrimary,
            scaffoldState = scaffoldState,
            topBar = {
                CustomTopAppBar(
                    onNavigationIconClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Log.d("TEST", metroUiState.metropolitan.toString())
                MetroMap(
                    metropolitan = metroUiState.metropolitan,
                    shortestPath = metroUiState.shortestPath,
                )
            }

            if (!sheetState.isVisible) {
                RouteInfo(
                    shortestPath = metroUiState.shortestPath,
                    onCloseClick = {
                        scope.launch {
                            metroViewModel.resetShortestPath()
                            metroViewModel.updateStartStationQueryValue(TextFieldValue(""))
                            metroViewModel.updateEndStationQueryValue(TextFieldValue(""))
                            sheetState.partialExpand()
                        }
                    }
                )
            }
        }
    }
}