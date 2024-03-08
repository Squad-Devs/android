package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState
import com.shdwraze.metro.common.Constants.DEFAULT_ZOOM
import com.shdwraze.metro.common.Constants.KHARKIV_FOCUS
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.model.ShortestPath
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

const val POLYLINE_WIDTH = 14f

@Composable
fun MetroMap(
    metropolitan: Metropolitan = Metropolitan(),
    onCalculateButtonClick: (Int, Int) -> Unit = { _, _ -> },
    onResetButtonClick: () -> Unit = {},
    shortestPath: ShortestPath = ShortestPath()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            KHARKIV_FOCUS,
            DEFAULT_ZOOM
        )
    }

    var stationFromId by remember { mutableStateOf(TextFieldValue("")) }
    var stationToId by remember { mutableStateOf(TextFieldValue("")) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(400.dp)) {
            CustomGoogleMap(
                cameraPositionState = cameraPositionState,
                metropolitan = metropolitan,
                shortestPath = shortestPath
            )
        }

        Row {
            OutlinedTextField(
                value = stationFromId,
                onValueChange = { stationFromId = it },
                label = { Text(text = "Enter station from ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = stationToId,
                onValueChange = { stationToId = it },
                label = { Text(text = "Enter station to ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Button(onClick = {
            onCalculateButtonClick(
                stationFromId.text.toInt(),
                stationToId.text.toInt()
            )
        }) {
            Text(text = "Calculate")
        }
        Button(onClick = { onResetButtonClick() }) {
            Text(text = "Reset")
        }
    }
}

@Preview
@Composable
fun MetroMapPreview() {
    MetroTheme {
        MetroMap()
    }
}