package com.example.users.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.texts.IconText
import com.example.users.R

@Composable
fun StarsAndLangRow(
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
        StarsAndLangRow(
            numberOfStars = "23",
            language = "Vue",
        )
    }
}
