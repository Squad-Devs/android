package com.shdwraze.metro.presentation.ui.screens.metro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.common.Constants.DEFAULT_CITY
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.domain.usecase.common.GetLinesUseCase
import com.shdwraze.metro.domain.usecase.station.GetStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MetroViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    private val getLinesUseCase: GetLinesUseCase
) : ViewModel() {

    var metroUiState: MetroUiState by mutableStateOf(MetroUiState.Loading)
        private set

    var currentStation = mutableStateOf<Station?>(null)

    var lines by mutableStateOf<List<String>>(emptyList())
        private set

    init {
        getLines()
        getStations()
    }

    fun getStations(line: String? = null) {
        viewModelScope.launch {
            metroUiState = try {
                MetroUiState.Success(getStationsUseCase.invoke(DEFAULT_CITY, line).first())
            } catch (e: IOException) {
                MetroUiState.Error
            }
        }
    }

    private fun getLines() {
        viewModelScope.launch {
            val newLines = try {
                getLinesUseCase.invoke().first()
            } catch (e: Exception) {
                emptyList()
            }
            lines = newLines
        }
    }

    fun findStationById(stationId: String) {
        if (metroUiState is MetroUiState.Success) {
            currentStation.value =
                (metroUiState as MetroUiState.Success).stations.find { it.id == stationId }
        }
    }

    fun setCurrentStation(station: Station) {
        currentStation.value = station
    }
}