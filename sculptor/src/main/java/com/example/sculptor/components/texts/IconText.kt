package com.example.sculptor.components.texts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sculptor.R
import com.example.sculptor.SculptorTheme

@Composable
fun IconText(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(12.dp),
            painter = icon,
            contentDescription = text,
        )
        Spacer(modifier = Modifier.width(SculptorTheme.space.quarter))
        Text(
            text = text,
            style =
                SculptorTheme.typography.tiny.copy(
                    color = Color.Black,
                ),
        )
    }
}

@Preview
@Composable
private fun IconTextPreview() {
    SculptorTheme {
        IconText(
            icon = painterResource(id = R.drawable.ic_home),
            text = "Home",
        )
    }
}
