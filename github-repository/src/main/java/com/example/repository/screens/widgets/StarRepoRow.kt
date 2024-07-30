package com.example.repository.screens.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.repository.R
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.texts.IconText

@Composable
fun StarRepoRow(
    numberOfStars: String,
    language: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.threeQuarters),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconText(
            icon = painterResource(id = R.drawable.ic_star),
            text = numberOfStars,
        )

        IconText(
            icon = painterResource(id = R.drawable.ic_circle),
            text = language,
        )
    }
}

@Preview
@Composable
fun StarRepoRowPreview() {
    SculptorTheme {
        StarRepoRow(
            numberOfStars = "23",
            language = "Vue",
        )
    }
}
