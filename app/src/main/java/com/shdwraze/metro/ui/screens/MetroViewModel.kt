package com.shdwraze.metro.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.network.MetroApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MetroUiState {
    data class Success(val stations: String) : MetroUiState
    object Error : MetroUiState
    object Loading : MetroUiState
}

class MetroViewModel : ViewModel() {

    var metroUiState: MetroUiState by mutableStateOf(MetroUiState.Loading)
        private set

    init {
        getStations()
    }

    fun getStations() {
        viewModelScope.launch {
            try {
                val listResult = MetroApi.retrofitService.getStations()
                metroUiState = MetroUiState.Success(
                    "Success: ${listResult.size} retrieved"
                )
            } catch (e: IOException) {
                metroUiState = MetroUiState.Error
            }
        }
    }
}

