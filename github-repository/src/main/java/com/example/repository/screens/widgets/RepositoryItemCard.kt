package com.example.repository.screens.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.sculptor.components.tags.TagOne

@Suppress("MagicNumber")
@Composable
fun RepositoryItemWidget(
    modifier: Modifier = Modifier,
    imageUrl: String,
    repositoryName: String,
    language: String,
    description: String,
    tags: List<String>,
    numberOfStars: Int,
    onCardClick: () -> Unit = {},
) {
    OutlinedCard(
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
            modifier = modifier.padding(SculptorTheme.space.one),
            verticalArrangement = Arrangement.spacedBy(SculptorTheme.space.one),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ImageTitleRow(
                    modifier = Modifier.weight(1f),
                    repositoryName = repositoryName,
                    imageUrl = imageUrl,
                )

                Spacer(modifier = Modifier.width(SculptorTheme.space.one))

                StarRepoRow(
                    numberOfStars = "$numberOfStars",
                    language = language,
                )
            }

            Text(
                text = description,
                style =
                    SculptorTheme.typography.labelThin.copy(
                        color = Color.Black,
                    ),
            )

            Row(horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.one)) {
                tags.forEach { tag ->
                    TagOne(text = tag)
                }
            }
        }
    }
}

@Preview
@Composable
private fun RepositoryItemWidgetPreview() {
    SculptorTheme {
        RepositoryItemWidget(
            imageUrl = "https://placehold.co/600x400",
            repositoryName = "Vundererekhgjkhhkgfjklgfhjkljkgjfhkljkgjf/Python",
            language = "Python",
            description =
                "These are random words that will be replaced in due time. " +
                    "Config files for my github profile",
            tags = listOf("Issues: 10"),
            numberOfStars = 10,
        )
    }
}
