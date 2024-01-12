package com.shdwraze.metro.presentation.ui.components.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

@Composable
fun CustomButton(
    width: Dp = 160.dp,
    height: Dp = 34.dp,
    text: String = "Button",
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {},
    isActive: Boolean = true // for future
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(width, height),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xe3e3e3)
@Composable
fun CustomButtonPreview() {
    MetroTheme {
        CustomButton()
    }
}