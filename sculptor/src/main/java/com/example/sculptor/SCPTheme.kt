package com.example.sculptor

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.sculptor.colors.SCPColor
import com.example.sculptor.spacing.SCPSpacing
import com.example.sculptor.typography.SCPTypography

internal val LocalTypography = staticCompositionLocalOf { SCPTypography() }

internal val LocalSpacing = staticCompositionLocalOf { SCPSpacing() }

internal val LocalColors = staticCompositionLocalOf { SCPColor() }

@Immutable
data class SLPTheme(
    val name: String = "Sculptor default theme",
    val colors: SCPColor = SCPColor(),
    val typography: SCPTypography = SCPTypography(),
    val space: SCPSpacing = SCPSpacing(),
)
