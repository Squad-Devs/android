package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState
import com.shdwraze.metro.common.Constants.DEFAULT_ZOOM
import com.shdwraze.metro.common.Constants.KHARKIV_FOCUS
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.model.ShortestPath
import com.shdwraze.metro.presentation.ui.components.common.AutoCompleteTextField
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

const val POLYLINE_WIDTH = 14f

@Composable
fun MetroMap(
    metropolitan: Metropolitan = Metropolitan(),
    onCalculateButtonClick: (String, String) -> Unit = { _, _ -> },
    onResetButtonClick: () -> Unit = {},
    shortestPath: ShortestPath = ShortestPath(),
    stationsMap: Map<String, Int> = mapOf()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            KHARKIV_FOCUS,
            DEFAULT_ZOOM
        )
    }

    val focusManager = LocalFocusManager.current

    var stationFromName by remember { mutableStateOf(TextFieldValue("")) }

    var stationToName by remember { mutableStateOf(TextFieldValue("")) }

    val heightTextFields by remember { mutableStateOf(55.dp) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var stationFromExpanded by remember { mutableStateOf(false) }

    var stationToExpanded by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(400.dp)) {
            CustomGoogleMap(
                cameraPositionState = cameraPositionState,
                metropolitan = metropolitan,
                shortestPath = shortestPath
            )
        }

        Column(
            modifier = Modifier
                .clickable(interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        stationFromExpanded = false
                        stationToExpanded = false
                    })
                .fillMaxWidth()
        ) {
            AutoCompleteTextField(
                value = stationFromName,
                onValueChange = { newValue ->
                    stationFromName = newValue
                    stationFromExpanded = true
                },
                heightTextFields = heightTextFields,
                onGloballyPositioned = {
                    textFieldSize = it.size.toSize()
                },
                expanded = stationFromExpanded,
                onExpandedChange = { stationFromExpanded = it },
                stationsMap = stationsMap,
                textFieldSize = textFieldSize,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(16.dp))
            AutoCompleteTextField(
                value = stationToName,
                onValueChange = { newValue ->
                    stationToName = newValue
                    stationToExpanded = true
                },
                heightTextFields = heightTextFields,
                onGloballyPositioned = {
                    textFieldSize = it.size.toSize()
                },
                expanded = stationToExpanded,
                onExpandedChange = { stationToExpanded = it },
                stationsMap = stationsMap,
                textFieldSize = textFieldSize,
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                onCalculateButtonClick(
                    stationFromName.text,
                    stationToName.text
                )
                focusManager.clearFocus()
            }) {
                Text(text = "Calculate")
            }
            Button(onClick = {
                onResetButtonClick()
                stationFromName = TextFieldValue("")
                stationToName = TextFieldValue("")
                focusManager.clearFocus()
            }) {
                Text(text = "Reset")
            }
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