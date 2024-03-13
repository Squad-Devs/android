package com.shdwraze.metro.presentation.ui.components.main.bottomsheetcontent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.shdwraze.metro.R
import com.shdwraze.metro.presentation.ui.components.common.AutoCompleteTextField

@Composable
fun BottomSheetContent(
    onCalculateButtonClick: (String, String) -> Unit = { _, _ -> },
    onResetButtonClick: () -> Unit = {},
    stationsMap: Map<String, Int> = mapOf(),
    startStationQueryValue: TextFieldValue = TextFieldValue(""),
    endStationQueryValue: TextFieldValue = TextFieldValue(""),
    onStartStationQueryValueChange: (TextFieldValue) -> Unit = {},
    onEndStationQueryValueChange: (TextFieldValue) -> Unit = {},
    onActionStart: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    val heightTextFields by remember { mutableStateOf(55.dp) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var startStationDropdownExpanded by remember { mutableStateOf(false) }

    var endStationDropdownExpanded by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    startStationDropdownExpanded = false
                    endStationDropdownExpanded = false
                }
            )
            .height(640.dp)
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.btm_sh_text_path_search),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.size(16.dp))
        AutoCompleteTextField(
            value = startStationQueryValue,
            onValueChange = { newValue ->
                onStartStationQueryValueChange(newValue)
                startStationDropdownExpanded = true
            },
            heightTextFields = heightTextFields,
            onGloballyPositioned = {
                textFieldSize = it.size.toSize()
            },
            expanded = startStationDropdownExpanded,
            onExpandedChange = { startStationDropdownExpanded = it },
            stationsMap = stationsMap,
            textFieldSize = textFieldSize,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        onActionStart()
                    }
                },
            label = stringResource(id = R.string.textfield_text_where_question),
            leadingIcon = Icons.Default.LocationOn
        )
        Spacer(modifier = Modifier.size(8.dp))
        AutoCompleteTextField(
            value = endStationQueryValue,
            onValueChange = { newValue ->
                onEndStationQueryValueChange(newValue)
                endStationDropdownExpanded = true
            },
            heightTextFields = heightTextFields,
            onGloballyPositioned = {
                textFieldSize = it.size.toSize()
            },
            expanded = endStationDropdownExpanded,
            onExpandedChange = { endStationDropdownExpanded = it },
            stationsMap = stationsMap,
            textFieldSize = textFieldSize,
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.textfield_text_where_2take_question),
            leadingIcon = Icons.Default.Star // todo need to change
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                onCalculateButtonClick(
                    startStationQueryValue.text,
                    endStationQueryValue.text
                )
                focusManager.clearFocus()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.btn_text_search))
        }
        Button(
            onClick = {
                onResetButtonClick()
                focusManager.clearFocus()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Reset")
        }
    }
}