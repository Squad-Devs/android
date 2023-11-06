package com.shdwraze.metro.presentation.ui.screens.metro

import com.shdwraze.metro.data.model.Station

sealed interface MetroUiState {
    data class Success(val stations: List<Station>) : MetroUiState
    object Error : MetroUiState
    object Loading : MetroUiState
}