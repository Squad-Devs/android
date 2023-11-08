package com.shdwraze.metro.presentation.ui.screens.metro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.common.Constants.DEFAULT_CITY
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.domain.usecase.station.GetStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MetroViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase
) : ViewModel() {

    var metroUiState: MetroUiState by mutableStateOf(MetroUiState.Loading)
        private set

    var currentStation = mutableStateOf<Station?>(null)

    init {
        getStations()
    }

    private fun getStations() {
        viewModelScope.launch {
            metroUiState = try {
                MetroUiState.Success(getStationsUseCase.invoke(DEFAULT_CITY).first())
            } catch (e: IOException) {
                MetroUiState.Error
            }
        }
    }

    fun setCurrentStation(station: Station) {
        currentStation.value = station
    }
}