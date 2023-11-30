package com.shdwraze.metro.presentation.ui.screens.station

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.domain.usecase.station.GetStationByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationViewModel @Inject constructor(
    private val getStationByIdUseCase: GetStationByIdUseCase
) : ViewModel() {

    var currentStation = mutableStateOf<Station?>(null)
        private set

    fun setCurrentStation(station: Station) {
        currentStation.value = station
    }

    fun findStationById(stationId: String) {
        viewModelScope.launch {
            currentStation.value = getStationByIdUseCase(stationId).first()
        }
    }
}