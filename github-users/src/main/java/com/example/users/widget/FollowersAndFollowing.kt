package com.example.users.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.texts.IconText
import com.example.users.R

@Composable
fun FollowersAndFollowing(
    modifier: Modifier = Modifier,
    followers: Int,
    following: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half),
    ) {
        IconText(
            icon = painterResource(id = R.drawable.ic_people),
            text = "$followers followers .",
        )

        Text(
            text = "$following following",
            style =
                SculptorTheme.typography.tiny.copy(
                    color = Color.Black,
                ),
        )
    }
}

@Preview
@Composable
private fun FollowersAndFollowingPreview() {
    SculptorTheme {
        FollowersAndFollowing(
            followers = 100,
            following = 100,
        )
    }
}
