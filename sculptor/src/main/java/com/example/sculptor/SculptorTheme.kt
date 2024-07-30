package com.example.sculptor

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.sculptor.colors.SCPColor
import com.example.sculptor.spacing.SCPSpacing
import com.example.sculptor.typography.SCPTypography

/**
 * Use this theme in clients.
 */
@Composable
fun SculptorTheme(
    theme: SLPTheme = SCPThemeManager.getCurrentTheme().value,
    content: @Composable () -> Unit,
) {
    ProvideCompositionLocal(theme = theme) {
        MaterialTheme(
            colorScheme = SculptorTheme.colors.toMaterial3Colors(),
            typography = SculptorTheme.typography.toMaterial3Typography(),
            content = content,
        )
    }
}

@Composable
private fun ProvideCompositionLocal(
    theme: SLPTheme,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalTypography provides theme.typography,
        LocalSpacing provides theme.space,
        content = content,
    )
}

/**
 * Get theme properties using this.
 */
object SculptorTheme {
    val typography: SCPTypography
        @Composable
        get() = LocalTypography.current

    val space: SCPSpacing
        @Composable
        get() = LocalSpacing.current

    val colors: SCPColor
        @Composable
        get() = LocalColors.current
}
