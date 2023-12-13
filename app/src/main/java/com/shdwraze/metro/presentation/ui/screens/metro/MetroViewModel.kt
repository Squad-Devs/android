package com.shdwraze.metro.presentation.ui.screens.metro

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.common.Constants.DEFAULT_CITY
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.domain.usecase.common.GetLinesUseCase
import com.shdwraze.metro.domain.usecase.station.GetStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MetroViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    private val getLinesUseCase: GetLinesUseCase
) : ViewModel() {

    private val _metroUiState = MutableStateFlow(MetroUiState())
    val metroUiState = _metroUiState.asStateFlow()

    var lines by mutableStateOf<List<String>>(emptyList())
        private set

    init {
        getLines()
        getStations()
    }

    fun getStations(line: String? = null) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("DEBUG", "CURRENT LINE = $line")
                getStationsUseCase(DEFAULT_CITY, line).collect {
                    when (it) {
                        is ApiResult.Loading -> {
                            setLoading(true)
                        }

                        is ApiResult.Success -> {
                            setStations(it.value)
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

    private fun setStations(list: List<Station>) {
        Log.d("DEBUG", "BEGIN UPDATE STATIONS LIST")
        _metroUiState.update { currentState ->
            currentState.copy(
                stations = list
            )
        }
        Log.d("DEBUG", "CURRENT STATIONS = $list")
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

    private fun setLoading(isLoading: Boolean) {
        _metroUiState.update { currentState ->
            currentState.copy(
                isLoading = isLoading
            )
        }
    }

    private fun setError(isError: Boolean) {
        _metroUiState.update { currentState ->
            currentState.copy(
                isError = isError
            )
        }
    }
}