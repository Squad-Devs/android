package com.shdwraze.metro.presentation.ui.components.metro

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LinesDropdownMenu(
    lines: List<String> = listOf("A", "B", "C"),
    expanded: Boolean = true,
    onDismissRequest: () -> Unit = {},
    onDropdownItemClick: (String) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier.defaultMinSize(96.dp)
    ) {
        lines.map { line ->
            Text(
                text = line,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onDropdownItemClick(line)
                    }
            )
        }
    }
}