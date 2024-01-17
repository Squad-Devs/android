package com.shdwraze.metro.presentation.ui.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shdwraze.metro.R
import com.shdwraze.metro.presentation.ui.components.common.CustomButton
import com.shdwraze.metro.presentation.ui.components.welcome.selector.SwitchBothTwoItems
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

@Composable
fun CitySelectorScreen() {
    var selected by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.city_selector_what_city_are_you_from),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.size(24.dp))
        SwitchBothTwoItems(
            onItemClick = { selected = it },
            isSelected = { it == selected },
            itemDetails = listOf(
                Pair(R.drawable.ic_kyiv, stringResource(R.string.city_kyiv)),
                Pair(R.drawable.ic_kharkiv, stringResource(R.string.city_kharkiv)),
            )
        )
        Spacer(modifier = Modifier.size(24.dp))
        CustomButton(
            text = stringResource(R.string.button_continue),
            buttonColor = if (selected == 0) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.primary,
            textColor = if (selected == 0) MaterialTheme.colorScheme.onSecondaryContainer
            else MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(device = Devices.PIXEL_3A, showBackground = true, showSystemUi = true)
@Composable
fun CitySelectorScreenPreview() {
    MetroTheme {
        CitySelectorScreen()
    }
}