package com.example.sculptor.components.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme

@Composable
fun TagOne(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier =
            modifier
                .background(
                    color = SculptorTheme.colors.skyBlue,
                    shape = RoundedCornerShape(SculptorTheme.space.threeQuarters),
                )
                .padding(
                    vertical = SculptorTheme.space.quarter,
                    horizontal = SculptorTheme.space.threeQuarters,
                ),
    ) {
        Text(
            text = text,
            style = SculptorTheme.typography.tinySemiBold,
            color = SculptorTheme.colors.oceanBlue,
        )
    }
}

@Preview
@Composable
fun TagOnePreview() {
    SculptorTheme {
        TagOne(
            text = "Design system",
        )
    }
}
