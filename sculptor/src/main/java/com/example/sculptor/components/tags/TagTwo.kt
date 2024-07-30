package com.example.sculptor.components.tags

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme

@Composable
fun TagTwo(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier =
            modifier
                .border(
                    width = SculptorTheme.space.halfThin,
                    color = SculptorTheme.colors.grey,
                    shape = RoundedCornerShape(size = SculptorTheme.space.half),
                )
                .padding(
                    horizontal = SculptorTheme.space.half,
                    vertical = SculptorTheme.space.quarter,
                ),
    ) {
        Text(
            text = text,
            style = SculptorTheme.typography.veryTiny,
        )
    }
}

@Preview
@Composable
private fun TagTwoPreview() {
    SculptorTheme {
        TagTwo(
            text = "Public",
        )
    }
}
