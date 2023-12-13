package com.shdwraze.metro.presentation.ui.screens.station

import com.shdwraze.metro.data.model.Station

data class StationUiState(
    val isError: Boolean = false,

    val isLoading: Boolean = false,

    val station: Station = Station()
)
