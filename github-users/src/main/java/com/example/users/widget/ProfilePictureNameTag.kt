package com.example.users.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.image.CircularImage
import com.example.sculptor.components.image.Type

@Composable
fun ProfilePictureNameTag(
    modifier: Modifier = Modifier,
    imageUrl: String,
    fullName: String,
    tag: String,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half)) {
        CircularImage(
            size = 45.dp,
            type = Type.Network,
            imageUrl = imageUrl,
        )

        Column(verticalArrangement = Arrangement.spacedBy(SculptorTheme.space.quarter)) {
            Text(
                text = fullName,
                style = SculptorTheme.typography.medium,
            )

            Text(
                text = tag,
                style =
                    SculptorTheme.typography.labelRegular.copy(
                        fontSize = 14.sp,
                    ),
            )
        }
    }
}

@Preview
@Composable
private fun ProfilePictureNameTagPreview() {
    SculptorTheme {
        ProfilePictureNameTag(
            imageUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            fullName = "Paige Brown",
            tag = "DynamicWebPaige",
        )
    }
}
