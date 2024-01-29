package com.shdwraze.metro.presentation.ui.screens.common

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shdwraze.metro.presentation.ui.components.main.map.MetroMap
import com.shdwraze.metro.presentation.ui.screens.metro.MetroViewModel

@Composable
fun TestScreen(
    metroViewModel: MetroViewModel = hiltViewModel()
) {
    val metroUiState by metroViewModel.metroUiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Log.d("TEST", metroUiState.metropolitan.toString())
        MetroMap(metroUiState.metropolitan)
    }
}