package com.example.sculptor.components.appbars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.R
import com.example.sculptor.SculptorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        colors =
            topAppBarColors(
                containerColor = Color.White,
            ),
        navigationIcon = {
            Icon(
                modifier =
                    Modifier
                        .padding(
                            start = SculptorTheme.space.half,
                            end = SculptorTheme.space.half,
                            top = SculptorTheme.space.half,
                            bottom = SculptorTheme.space.half,
                        )
                        .background(Color.White)
                        .width(SculptorTheme.space.one)
                        .height(SculptorTheme.space.one)
                        .clickable { onBackClick() },
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription =
                    stringResource(id = R.string.sculptor_top_app_bar_content_description),
            )
        },
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
fun ActionTopAppBarPreview() {
    SculptorTheme {
        ActionTopAppBar(
            title = "Users",
            onBackClick = {},
        )
    }
}
