package com.shdwraze.metro.presentation.ui.components.welcome.language_selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shdwraze.metro.R
import com.shdwraze.metro.presentation.ui.theme.MetroTheme

@Composable
fun Item(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.ic_en,
    title: String = "English",
    onItemClick: () -> Unit = {},
    isSelected: Boolean = true
) {
    val shape = RoundedCornerShape(16.dp)

    Box(modifier = Modifier.size(115.dp)) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x66000000),
                    ambientColor = Color(0x66000000),
                    shape = shape
                )
                .background(MaterialTheme.colorScheme.background, shape = shape)
                .clickable {
                    onItemClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = "Icon",
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Color(0xFF00210E), shape)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xe3e3e3)
@Composable
fun ItemPreview() {
    MetroTheme {
        Item()
    }
}