package com.shdwraze.metro.presentation.ui.screens.common

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shdwraze.metro.data.model.ShortestPath
import com.shdwraze.metro.presentation.ui.components.common.CustomTopAppBar
import com.shdwraze.metro.presentation.ui.components.main.bottomsheetcontent.BottomSheetContent
import com.shdwraze.metro.presentation.ui.components.main.map.MetroMap
import com.shdwraze.metro.presentation.ui.components.main.sidebar.SidebarMenu
import com.shdwraze.metro.presentation.ui.screens.metro.MetroViewModel
import com.shdwraze.metro.presentation.ui.theme.MetroTheme
import com.shdwraze.metro.presentation.ui.utils.IconColorPicker.getMarkerIconResource
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

@Composable
fun RouteInfo(
    shortestPath: ShortestPath = ShortestPath(),
    onCloseClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .heightIn(max = 200.dp)
                .padding(16.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = MaterialTheme.colorScheme.onPrimary)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        imageVector = Icons.Default.Close, contentDescription = "close",
                        tint = Color.Black.copy(alpha = 0.25f)
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Тривалість поїздки ~${shortestPath.travelTimeInMinutes} хв",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                LazyRow {
                    items(shortestPath.path) { station ->
                        val stationLineColor = Color(station.line.color)
                        val stationMarker = getMarkerIconResource(station.line.color)

                        Column(
                            modifier = Modifier
                                .width(128.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(20.dp)
                                    .fillMaxWidth()
                            ) {
                                Canvas(
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .fillMaxWidth()
                                ) {
                                    drawLine(
                                        color = stationLineColor,
                                        start = Offset.Zero,
                                        end = Offset(size.width, 0f),
                                        strokeWidth = 15f
                                    )
                                }
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = stationMarker),
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                            Text(
                                text = station.name,
                                modifier = Modifier.fillMaxSize(),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .height(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(modifier = Modifier.fillMaxWidth()) {
                                val dashWidth = 10f
                                val dashGap = 10f
                                val dashPathEffect =
                                    PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), 0f)
                                drawLine(
                                    color = stationLineColor,
                                    start = Offset.Zero,
                                    end = Offset(size.width, 0f),
                                    strokeWidth = 15f,
                                    pathEffect = dashPathEffect
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun RouteInformationPreview() {
    MetroTheme {
        RouteInfo()
    }
}