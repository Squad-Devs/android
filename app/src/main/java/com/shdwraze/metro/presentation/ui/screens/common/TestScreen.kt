package com.shdwraze.metro.presentation.ui.screens.common

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shdwraze.metro.presentation.ui.components.main.bottomsheetcontent.BottomSheetContent
import com.shdwraze.metro.presentation.ui.components.main.map.MetroMap
import com.shdwraze.metro.presentation.ui.screens.metro.MetroViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
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
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp),
                drawerContainerColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text(
                    text = "Coming soon...",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                )
                Spacer(modifier = Modifier.height(36.dp))
                NavigationDrawerItem(
                    label = {
                        Text(text = "Item example")
                    },
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Default.Create, contentDescription = "example")
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedContainerColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
                NavigationDrawerItem(
                    label = {
                        Text(text = "Item example")
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "example")
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = MaterialTheme.colorScheme.background,
                        unselectedContainerColor = MaterialTheme.colorScheme.onPrimary
                    )
                )

            }
        },
        drawerState = drawerState
    ) {
        BottomSheetScaffold(
            sheetContent = {
                BottomSheetContent(
                    onCalculateButtonClick = { startStation, endStation ->
                        metroViewModel.getShortestPath(startStation, endStation)
                        scope.launch { sheetState.partialExpand() }
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
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Metro App Name",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        titleContentColor = Color.Black
                    ),
                    modifier = Modifier
                        .shadow(elevation = 8.dp),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "menu",
                                modifier = Modifier.fillMaxSize()
                            )
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
        }
    }
}