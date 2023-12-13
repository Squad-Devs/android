package com.shdwraze.metro.presentation.ui.screens.station

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.domain.usecase.station.GetStationByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StationViewModel @Inject constructor(
    private val getStationByIdUseCase: GetStationByIdUseCase
) : ViewModel() {

    private val _stationUiState = MutableStateFlow(StationUiState())
    val stationUiState = _stationUiState.asStateFlow()

    fun getStationById(stationId: String) {
        if (_stationUiState.value.station.id == stationId) {
            return
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getStationByIdUseCase(stationId).collect {
                    when (it) {
                        is ApiResult.Loading -> {
                            setLoading(true)
                        }

                        is ApiResult.Success -> {
                            setCurrentStation(it.value)
                            setLoading(false)
                        }

                        else -> {
                            setLoading(false)
                            setError(true)
                        }
                    }
                }
            }
        }
    }

    fun setCurrentStation(station: Station) {
        _stationUiState.update { currentState ->
            currentState.copy(
                station = station
            )
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _stationUiState.update { currentState ->
            currentState.copy(
                isLoading = isLoading
            )
        }
    }

    private fun setError(isError: Boolean) {
        _stationUiState.update { currentState ->
            currentState.copy(
                isError = isError
            )
        }
    }
}