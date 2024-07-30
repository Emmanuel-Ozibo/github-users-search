package com.example.sculptor.components.texts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.R
import com.example.sculptor.SculptorTheme

@Composable
fun LinkText(
    modifier: Modifier = Modifier,
    painter: Painter,
    link: String,
    onLinkClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.clickable { onLinkClick() },
        horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.quarter),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(SculptorTheme.space.one),
            painter = painter,
            tint = SculptorTheme.colors.lighterDark.copy(alpha = 0.5f),
            contentDescription = "Link",
        )
        Text(
            text = link,
            style =
                SculptorTheme.typography.tinySemiBold.copy(
                    color = SculptorTheme.colors.lighterDark,
                ),
        )
    }
}

@Preview
@Composable
private fun LinkTextPreview() {
    SculptorTheme {
        LinkText(
            link = "http://www.paige.com",
            painter = painterResource(id = R.drawable.ic_link),
        )
    }
}
