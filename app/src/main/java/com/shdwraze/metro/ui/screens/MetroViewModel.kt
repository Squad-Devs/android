package com.shdwraze.metro.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.shdwraze.metro.MetroApplication
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