package com.example.repository.screens.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.image.CircularImage
import com.example.sculptor.components.image.Type
import com.example.sculptor.components.texts.TwoStyledText

@Composable
fun ImageTitleRow(
    modifier: Modifier = Modifier,
    imageUrl: String,
    repositoryName: String,
) {
    val nameAndLanguage = repositoryName.split("/")
    val repoName = nameAndLanguage[0]
    val language = nameAndLanguage[1]

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularImage(size = 30.dp, type = Type.Network, imageUrl = imageUrl)
        TwoStyledText(firstText = repoName, secondText = language)
    }
}

@Preview
@Composable
private fun ImageTitleRowPreview() {
    SculptorTheme {
        ImageTitleRow(
            repositoryName = "Vundere/Phyton",
            imageUrl = "",
        )
    }
}
