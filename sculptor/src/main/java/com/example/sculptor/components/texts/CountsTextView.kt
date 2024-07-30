package com.example.sculptor.components.texts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sculptor.SculptorTheme

const val REPOSITORY_WEIGHT = 0.4f

@Composable
fun CountsTextView(
    modifier: Modifier = Modifier,
    title: String,
    count: Int,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
    ) {
        Column(
            modifier = modifier.weight(REPOSITORY_WEIGHT),
            verticalArrangement = Arrangement.spacedBy(SculptorTheme.space.quarter),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style =
                        SculptorTheme.typography.tinySemiBold.copy(
                            color = Color.Black,
                        ),
                )

                Box(
                    modifier =
                        Modifier
                            .background(
                                color = SculptorTheme.colors.almostWhite,
                                shape = RoundedCornerShape(size = SculptorTheme.space.half),
                            )
                            .padding(
                                horizontal = SculptorTheme.space.half,
                                vertical = SculptorTheme.space.quarter,
                            ),
                ) {
                    Text(
                        text = "$count",
                        style = SculptorTheme.typography.veryTinySemiBold,
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = SculptorTheme.colors.totalDark),
            )
        }

        Spacer(
            modifier =
                Modifier
                    .padding(0.dp)
                    .weight(1f)
                    .height(1.dp)
                    .background(color = SculptorTheme.colors.almostWhite),
        )
    }
}

@Preview
@Composable
private fun CountsTextViewPreview() {
    SculptorTheme {
        CountsTextView(
            title = "Repositories",
            count = 2000,
        )
    }
}
