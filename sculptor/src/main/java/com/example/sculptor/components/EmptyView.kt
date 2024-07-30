package com.example.sculptor.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sculptor.R
import com.example.sculptor.SculptorTheme

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    message: String,
    textColor: Color = SculptorTheme.colors.mediumGrey,
    @DrawableRes icon: Int = R.drawable.ic_empty_search,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "EmptyView_infinite")

    val transY by infiniteTransition.animateFloat(
        label = "translationY",
        initialValue = 0f,
        targetValue = 20f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
    )

    Column(
        modifier = modifier.offset(y = transY.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SculptorTheme.space.two, alignment = Alignment.CenterVertically),
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = message,
        )

        Text(
            text = message,
            textAlign = TextAlign.Center,
            style =
                SculptorTheme.typography.labelRegular.copy(
                    color = textColor,
                ),
        )
    }
}

@Preview
@Composable
private fun EmptyViewPreview() {
    SculptorTheme {
        EmptyView(
            message = "We’ve searched the ends of the earth, repository not found, please try again",
        )
    }
}
