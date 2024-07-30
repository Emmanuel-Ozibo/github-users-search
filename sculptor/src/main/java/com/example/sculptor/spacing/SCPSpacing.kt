package com.example.sculptor.spacing

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class SCPSpacing(
    val thin: Dp = 1.dp,
    val halfThin: Dp = 0.5.dp,
    val none: Dp = 0.dp,
    val quarter: Dp = 4.dp,
    val half: Dp = 8.dp,
    val threeQuarters: Dp = 12.dp,
    val one: Dp = 16.dp,
    val oneAndHalf: Dp = 24.dp,
    val two: Dp = 32.dp,
    val twoAndHalf: Dp = 40.dp,
    val three: Dp = 48.dp,
    val four: Dp = 64.dp,
    val five: Dp = 80.dp,
)
