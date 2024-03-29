package com.shdwraze.metro.presentation.ui.screens.metro

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.metro.common.Constants.DEFAULT_CITY
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.model.ShortestPath
import com.shdwraze.metro.domain.usecase.common.GetLinesUseCase
import com.shdwraze.metro.domain.usecase.metropolitan.GetMetropolitanUseCase
import com.shdwraze.metro.domain.usecase.path.GetShortestPathUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MetroViewModel @Inject constructor(
    private val getMetropolitanUseCase: GetMetropolitanUseCase,
    private val getLinesUseCase: GetLinesUseCase,
    private val getShortestPathUseCase: GetShortestPathUseCase
) : ViewModel() {

    private val _metroUiState = MutableStateFlow(MetroUiState())
    val metroUiState = _metroUiState.asStateFlow()

    private var lines by mutableStateOf<List<String>>(emptyList())

    init {
        getLines()
        getMetropolitan()
    }

    fun getShortestPath(stationFromId: Int, stationToId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getShortestPathUseCase(stationFromId, stationToId).collect {
                    when (it) {
                        is ApiResult.Loading -> {
                            setLoading(true)
                        }
                        is ApiResult.Success -> {
                            setShortestPath(it.value)
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

    fun resetShortestPath() {
        _metroUiState.update { currentState ->
            currentState.copy(
                shortestPath = ShortestPath()
            )
        }
    }

    private fun getMetropolitan() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getMetropolitanUseCase(DEFAULT_CITY).collect {
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

    private fun setShortestPath(shortestPath: ShortestPath) {
        Log.d("DEBUG", "BEGIN UPDATE PATH OBJ")
        _metroUiState.update { currentState ->
            currentState.copy(
                shortestPath = shortestPath
            )
        }
        Log.d("DEBUG", "CURRENT PATH = $shortestPath")
    }

    private fun setStations(metropolitan: Metropolitan) {
        Log.d("DEBUG", "BEGIN UPDATE METROPOLITAN OBJ")
        _metroUiState.update { currentState ->
            currentState.copy(
                metropolitan = metropolitan
            )
        }
        Log.d("DEBUG", "CURRENT METROPOLITAN = $metropolitan")
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