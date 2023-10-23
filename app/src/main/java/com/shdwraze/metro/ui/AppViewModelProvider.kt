package com.shdwraze.metro.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.shdwraze.metro.MetroApplication
import com.shdwraze.metro.ui.screens.MetroViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MetroViewModel(metroApplication().container.stationRepository)
        }
    }
}

fun CreationExtras.metroApplication(): MetroApplication =
    (this[APPLICATION_KEY] as MetroApplication)