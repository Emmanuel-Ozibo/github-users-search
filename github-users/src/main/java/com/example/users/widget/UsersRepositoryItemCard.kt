package com.example.users.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sculptor.SculptorTheme

@Suppress("MagicNumber")
@Composable
fun UsersRepositoryItemCard(
    modifier: Modifier = Modifier,
    description: String,
    forkedFrom: String,
    updatedAt: String,
    language: String,
    name: String,
    repo: String,
    numberOfStars: String,
    visibility: String,
    onCardClick: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        border =
            BorderStroke(
                width = 1.dp,
                color = Color(0xFFD9D9D9).copy(alpha = 0.6f),
            ),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        shape = RoundedCornerShape(SculptorTheme.space.quarter),
        onClick = onCardClick,
    ) {
        Column(
            modifier = Modifier.padding(SculptorTheme.space.one),
            verticalArrangement = Arrangement.spacedBy(SculptorTheme.space.one),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RepositoryNameAndTagRow(
                    modifier = Modifier.weight(1f),
                    visibility = visibility,
                    name = name,
                    repo = repo,
                )

                StarsAndLangRow(
                    numberOfStars = numberOfStars,
                    language = language,
                )
            }

            Description(description = description)

            Row(horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.one)) {
                Text(
                    text = forkedFrom,
                    style =
                        SculptorTheme.typography.tiny.copy(
                            color = Color.Black,
                        ),
                )

                Text(
                    text = updatedAt,
                    style =
                        SculptorTheme.typography.tiny.copy(
                            color = Color.Black,
                        ),
                )
            }
        }
    }
}

@Composable
fun Description(description: String) {
    Text(
        text = description,
        style =
            SculptorTheme.typography.labelThin.copy(
                color = Color.Black,
            ),
    )
}

@Preview
@Composable
fun UsersRepositoryItemCardPreview() {
    SculptorTheme {
        UsersRepositoryItemCard(
            description = "Complete framework to simplify the implementation of music commands using discord.js",
            forkedFrom = "Forked from discordify",
            updatedAt = "Updated 4 days ago",
            language = "Python",
            name = "Vundere",
            repo = "Vue",
            numberOfStars = "23",
            visibility = "Public",
            onCardClick = {},
        )
    }
}
