package com.shdwraze.metro.presentation.ui.screens.common

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.shdwraze.metro.presentation.ui.utils.extensions.noRippleClickable
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
        initialValue = SheetValue.PartiallyExpanded
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    var isMenuOpened by remember { mutableStateOf(false) }

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
                            isMenuOpened = true
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

    if (isMenuOpened) {
        SidebarMenu(
            onCloseClick = {
                isMenuOpened = false
            },
            visible = isMenuOpened
        )
    }
}

@Composable
fun SidebarMenu(
    onCloseClick: () -> Unit = {},
    visible: Boolean
) {
    val visibleState = remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        visibleState.value = visible
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f))
            .noRippleClickable { onCloseClick() }
    ) {
        AnimatedVisibility(
            visible = visibleState.value,
            enter = slideInHorizontally(
                animationSpec = tween(durationMillis = 100),
                initialOffsetX = { fullWidth -> -fullWidth / 3 }
            ) + fadeIn(animationSpec = tween(durationMillis = 100)),
            exit = slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessHigh),
                targetOffsetX = { 100 }
            ) + fadeOut()
        ) {
            Log.d("TEST", "I'm here")
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .background(color = MaterialTheme.colorScheme.onPrimary)
                    .noRippleClickable { }
                    .padding(16.dp)
            ) {
                Text(text = "Coming soon")
            }
        }
    }
}