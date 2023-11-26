package com.shdwraze.metro.presentation.ui.components.station

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun StationDetailsTitle(
    name: String
) {
    Text(
        text = name,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}