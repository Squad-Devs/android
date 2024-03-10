package com.shdwraze.metro.presentation.ui.components.main.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
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

    var stationFromName by remember { mutableStateOf("") }

    var stationToName by remember { mutableStateOf("") }

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
                .weight(1f)
                .clickable(interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        stationFromExpanded = false
                        stationToExpanded = false
                    })
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
                textFieldSize = textFieldSize
            )
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
                textFieldSize = textFieldSize
            )

            Button(onClick = {
                onCalculateButtonClick(
                    stationFromName,
                    stationToName
                )
            }) {
                Text(text = "Calculate")
            }
            Button(onClick = { onResetButtonClick() }) {
                Text(text = "Reset")
            }
        }
    }
}

@Composable
fun AutoCompleteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    heightTextFields: Dp,
    textFieldSize: Size,
    onGloballyPositioned: (LayoutCoordinates) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    stationsMap: Map<String, Int>,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .height(heightTextFields)
                .onGloballyPositioned {
                    onGloballyPositioned(it)
                },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { onExpandedChange(!expanded) }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = "arrow"
                    )
                }
            }
        )
    }

    AnimatedVisibility(visible = expanded) {
        AutoCompleteDropdown(
            value = value,
            textFieldSize = textFieldSize,
            stationsMap = stationsMap,
            onValueChange = onValueChange,
            onExpandedChange = onExpandedChange
        )
    }
}

@Composable
fun AutoCompleteDropdown(
    value: String,
    textFieldSize: Size,
    stationsMap: Map<String, Int>,
    onValueChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .width(textFieldSize.width.dp)
    ) {
        LazyColumn(modifier = Modifier.heightIn(max = 150.dp)) {
            if (value.isNotEmpty()) {
                items(stationsMap.keys.toList()
                    .filter {
                        it.lowercase().contains(value.lowercase())
                    }
                    .sorted()
                ) {
                    AutoCompleteItems(title = it) { title ->
                        onValueChange(title)
                        onExpandedChange(false)
                    }
                }
            } else {
                items(
                    stationsMap.keys.toList().sorted()
                ) {
                    AutoCompleteItems(title = it) { title ->
                        onValueChange(title)
                        onExpandedChange(false)
                    }
                }
            }
        }
    }
}

@Composable
fun AutoCompleteItems(
    title: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title)
    }
}

@Preview
@Composable
fun MetroMapPreview() {
    MetroTheme {
        MetroMap()
    }
}