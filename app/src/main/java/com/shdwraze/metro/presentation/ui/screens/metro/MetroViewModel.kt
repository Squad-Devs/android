package com.shdwraze.metro.presentation.ui.screens.metro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.data.StationRepository
import com.shdwraze.metro.network.Station
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MetroUiState {
    data class Success(val stations: List<Station>) : MetroUiState
    object Error : MetroUiState
    object Loading : MetroUiState
}

class MetroViewModel(private val stationRepository: StationRepository) : ViewModel() {

    var metroUiState: MetroUiState by mutableStateOf(MetroUiState.Loading)
        private set

    init {
        getStations()
    }

    fun getStations() {
        viewModelScope.launch {
            metroUiState = try {
                MetroUiState.Success(stationRepository.getStations())
            } catch (e: IOException) {
                MetroUiState.Error
            }
        }
    }
}