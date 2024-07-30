package com.example.users.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.tags.TagTwo
import com.example.sculptor.components.texts.TwoStyledText

@Composable
fun RepositoryNameAndTagRow(
    modifier: Modifier = Modifier,
    visibility: String,
    name: String,
    repo: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TwoStyledText(firstText = name, secondText = repo)

        TagTwo(text = visibility)
    }
}

@Preview
@Composable
fun RepositoryNameAndTagRowPreview() {
    SculptorTheme {
        RepositoryNameAndTagRow(
            visibility = "Public",
            name = "Vundere",
            repo = "Vue",
        )
    }
}
