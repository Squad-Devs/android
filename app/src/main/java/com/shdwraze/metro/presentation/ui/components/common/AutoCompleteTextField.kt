package com.shdwraze.metro.presentation.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.shdwraze.metro.R
import kotlinx.coroutines.delay

@Composable
fun AutoCompleteTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    heightTextFields: Dp,
    textFieldSize: Size,
    onGloballyPositioned: (LayoutCoordinates) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    stationsMap: Map<String, Int>,
    modifier: Modifier = Modifier,
    delayMillis: Long = 500,
    label: String = stringResource(R.string.textfield_text_where_question),
    leadingIcon: ImageVector = Icons.Default.LocationOn
) {
    val icon = if (expanded) Icons.Rounded.Close else Icons.Rounded.ArrowDropDown
    var showSuggestions by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        CustomTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                showSuggestions = false
            },
            modifier = Modifier
                .height(heightTextFields)
                .onGloballyPositioned { onGloballyPositioned(it) }
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    onExpandedChange(!expanded)
                }) {
                    Icon(imageVector = icon, contentDescription = "arrow")
                }
            },
            textStyle = TextStyle(color = Color.Black),
            label = label,
            leadingIcon = leadingIcon
        )

        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))
        ) {
            DropdownMenu(
                expanded = expanded && showSuggestions && value.text.isNotEmpty(),
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                    .shadow(elevation = 8.dp)
                    .background(color = MaterialTheme.colorScheme.onPrimary),
                properties = PopupProperties(
                    focusable = false,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            ) {
                val suggestions = stationsMap.keys.toList()
                    .filter { it.lowercase().contains(value.text.lowercase()) }
                    .sorted()

                suggestions.forEach { suggestion ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(
                                TextFieldValue(
                                    text = suggestion,
                                    selection = TextRange(suggestion.length)
                                )
                            )
                            onExpandedChange(false)
                        },
                        text = { Text(text = suggestion) }
                    )
                }
            }
        }

    }

    LaunchedEffect(value) {
        delay(delayMillis)
        if (value.text.isNotEmpty()) {
            showSuggestions = true
        }
    }
}