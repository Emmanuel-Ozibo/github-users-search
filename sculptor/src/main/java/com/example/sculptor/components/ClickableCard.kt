package com.example.sculptor.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sculptor.R
import com.example.sculptor.SculptorTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val ALPHA = 0.6f
val HEIGHT = 120.dp
val IMAGE_SIZE = 35.dp
const val DEFAULT_ANIM_DELAY = 200L
const val SCALE_FACTOR_IN = 0.8f
const val SCALE_FACTOR_OUT = 1f

@Composable
fun ClickableCard(
    title: String,
    iconRes: Int,
    onCardClick: () -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    var scaleFactor by remember { mutableFloatStateOf(1f) }

    val scaleXY by animateFloatAsState(targetValue = scaleFactor, label = "scaleAnimation")

    OutlinedCard(
        modifier = modifier.height(HEIGHT).scale(scaleXY),
        colors =
            CardDefaults.cardColors(
                containerColor = backgroundColor,
            ),
        border =
            BorderStroke(
                width = SculptorTheme.space.thin,
                color =
                    SculptorTheme.colors.strokeGrey
                        .copy(alpha = ALPHA),
            ),
        onClick = {
            scope.launch {
                scaleFactor = SCALE_FACTOR_IN
                delay(DEFAULT_ANIM_DELAY)
                scaleFactor = SCALE_FACTOR_OUT
                delay(DEFAULT_ANIM_DELAY)
                onCardClick()
            }
        },
        shape = RoundedCornerShape(SculptorTheme.space.quarter),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .padding(SculptorTheme.space.one),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Image(
                modifier =
                    Modifier
                        .size(IMAGE_SIZE)
                        .background(
                            color = Color.White.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(size = 4.dp),
                        )
                        .padding(8.dp),
                painter = painterResource(id = iconRes),
                contentDescription = "Icon",
            )
            Text(
                text = title,
                style = SculptorTheme.typography.medium,
            )
        }
    }
}

@Preview
@Composable
private fun ClickableCardPreview() {
    SculptorTheme {
        ClickableCard(
            title = "Users",
            iconRes = R.drawable.ic_user_outline,
            onCardClick = {},
            backgroundColor = SculptorTheme.colors.niceBlue,
        )
    }
}
