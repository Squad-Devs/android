package com.shdwraze.metro.presentation.ui.components.welcome.selector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

const val LEFT_ITEM_VALUE = 1
const val RIGHT_ITEM_VALUE = 2

@Composable
fun SwitchBothTwoItems(
    onItemClick: (Int) -> Unit = {},
    isSelected: (Int) -> Boolean = { false },
    itemDetails: List<Pair<Int, String>>
) {
    Box(
        contentAlignment = Alignment.Center
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Item(
                onItemClick = { onItemClick(LEFT_ITEM_VALUE) },
                isSelected = isSelected(LEFT_ITEM_VALUE),
                icon = itemDetails[0].first,
                title = itemDetails[0].second
            )
            Item(
                onItemClick = { onItemClick(RIGHT_ITEM_VALUE) },
                isSelected = isSelected(RIGHT_ITEM_VALUE),
                icon = itemDetails[1].first,
                title = itemDetails[1].second
            )
        }
    }
}
