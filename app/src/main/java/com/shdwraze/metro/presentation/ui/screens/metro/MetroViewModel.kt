package com.shdwraze.metro.presentation.ui.screens.metro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.data.repository.StationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MetroViewModel @Inject constructor(
    private val stationRepository: StationRepository
) : ViewModel() {

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