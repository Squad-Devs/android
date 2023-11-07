package com.shdwraze.metro.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.shdwraze.metro.presentation.navigation.NavGraph
import com.shdwraze.metro.presentation.ui.theme.MetroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MetroTheme {
                NavGraph()
            }
        }
    }
}