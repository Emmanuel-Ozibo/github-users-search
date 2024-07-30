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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.image.CircularImage
import com.example.sculptor.components.image.Type

const val ALPHA = 0.6f
val USER_IMAGE_SIZE = 25.dp

@Composable
fun UserItemCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    fullName: String,
    profileDescription: String,
    location: String,
    email: String,
    onCardClick: () -> Unit = {},
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        border =
            BorderStroke(
                width = SculptorTheme.space.thin,
                color = SculptorTheme.colors.strokeGrey.copy(alpha = ALPHA),
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(SculptorTheme.space.quarter),
        onClick = onCardClick,
    ) {
        Row(
            modifier = modifier.padding(SculptorTheme.space.one),
            horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half),
        ) {
            CircularImage(
                size = USER_IMAGE_SIZE,
                imageUrl = imageUrl,
                type = Type.Network,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(SculptorTheme.space.quarter),
            ) {
                Text(
                    text = fullName,
                    style =
                        SculptorTheme.typography.labelBold.copy(
                            color = SculptorTheme.colors.oceanBlue,
                        ),
                )

                Text(
                    text = fullName,
                    style = SculptorTheme.typography.tiny,
                )

                Text(
                    text = profileDescription,
                    style = SculptorTheme.typography.tinySemiBold,
                )

                LocationEmail(location = location, email = email)
            }
        }
    }
}

@Composable
fun LocationEmail(
    location: String,
    email: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half)) {
        Text(
            text = location,
            style =
                SculptorTheme.typography.veryTiny.copy(
                    color = SculptorTheme.colors.mediumGrey,
                ),
        )

        Text(
            text = email,
            style =
                SculptorTheme.typography.veryTiny.copy(
                    color = SculptorTheme.colors.mediumGrey,
                ),
        )
    }
}

@Preview
@Composable
private fun UserItemCardPreview() {
    SculptorTheme {
        UserItemCard(
            imageUrl = "https://placehold.co/600x400",
            fullName = "Paige Brown",
            profileDescription = "This is a random bio, which will be replace with actual content",
            location = "New York",
            email = "paige@gmail.com",
        )
    }
}
