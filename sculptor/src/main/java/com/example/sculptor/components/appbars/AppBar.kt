package com.example.sculptor.components.appbars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        colors =
            topAppBarColors(
                containerColor = Color.White,
            ),
        title = {
            Text(
                text = title,
                style = SculptorTheme.typography.heading,
            )
        },
    )
}

@Preview
@Composable
fun AppBarPreview() {
    SculptorTheme {
        AppBar(title = "Home")
    }
}
