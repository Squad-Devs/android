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
import com.shdwraze.metro.data.NetworkStationRepository
import com.shdwraze.metro.data.StationRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MetroUiState {
    data class Success(val stations: String) : MetroUiState
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
                val listResult = stationRepository.getStations()

                MetroUiState.Success(
                    "Success: ${listResult.size} retrieved"
                )
            } catch (e: IOException) {
                MetroUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MetroApplication)
                val stationRepository = application.container.stationRepository
                MetroViewModel(stationRepository = stationRepository)
            }
        }
    }
}