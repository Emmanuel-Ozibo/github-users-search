package com.example.githubsearch.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubsearch.R
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.ClickableCard
import com.example.sculptor.components.appbars.AppBar

@Composable
internal fun HomeScreen(
    onUsersCardClick: () -> Unit = {},
    onRepositoriesCardClick: () -> Unit = {},
) {
    Column {
        AppBar(title = "Home")

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        horizontal = SculptorTheme.space.one,
                        vertical = SculptorTheme.space.one,
                    ),
            horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half),
        ) {
            ClickableCard(
                modifier = Modifier.weight(1f),
                title = "Users",
                iconRes = R.drawable.ic_user_outline,
                backgroundColor = SculptorTheme.colors.niceBlue,
                onCardClick = onUsersCardClick,
            )

            ClickableCard(
                modifier = Modifier.weight(1f),
                title = "Repositories",
                iconRes = R.drawable.ic_code_box,
                backgroundColor = SculptorTheme.colors.nicePurple,
                onCardClick = onRepositoriesCardClick,
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    SculptorTheme {
        HomeScreen()
    }
}
