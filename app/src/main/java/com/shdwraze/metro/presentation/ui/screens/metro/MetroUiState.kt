package com.shdwraze.metro.presentation.ui.screens.metro

import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.model.ShortestPath
import com.shdwraze.metro.data.model.Station

data class MetroUiState (
    val isError: Boolean = false,

    val isLoading: Boolean = false,

    val metropolitan: Metropolitan = Metropolitan(),

    val shortestPath: ShortestPath = ShortestPath()
)