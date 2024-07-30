package com.example.sculptor.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
data class SCPColor(
    val primaryLight: Color = Color(0xFFFFFFFF),
    val onPrimaryLight: Color = Color(0xFFFFFFFF),
    val primaryContainerLight: Color = Color(0xFFE8DDFF),
    val onPrimaryContainerLight: Color = Color(0xFF1F1048),
    val secondaryLight: Color = Color(0xFF615B71),
    val onSecondaryLight: Color = Color(0xFFFFFFFF),
    val secondaryContainerLight: Color = Color(0xFFE7DEF8),
    val onSecondaryContainerLight: Color = Color(0xFF1D192B),
    val tertiaryLight: Color = Color(0xFF7D5261),
    val onTertiaryLight: Color = Color(0xFFFFFFFF),
    val tertiaryContainerLight: Color = Color(0xFFFFD9E3),
    val onTertiaryContainerLight: Color = Color(0xFF31101E),
    val errorLight: Color = Color(0xFFBA1A1A),
    val onErrorLight: Color = Color(0xFFFFFFFF),
    val errorContainerLight: Color = Color(0xFFFFDAD6),
    val onErrorContainerLight: Color = Color(0xFF410002),
    val backgroundLight: Color = Color(0xFFffFFFF),
    val onBackgroundLight: Color = Color(0xFF1C1B20),
    val surfaceLight: Color = Color(0xFFFDF7FF),
    val onSurfaceLight: Color = Color(0xFF1C1B20),
    val surfaceVariantLight: Color = Color(0xFFE6E0EC),
    val onSurfaceVariantLight: Color = Color(0xFF48454E),
    val outlineLight: Color = Color(0xFF79757F),
    val outlineVariantLight: Color = Color(0xFFCAC4CF),
    val scrimLight: Color = Color(0xFF000000),
    val inverseSurfaceLight: Color = Color(0xFF322F35),
    val inverseOnSurfaceLight: Color = Color(0xFFF5EFF7),
    val inversePrimaryLight: Color = Color(0xFFCEBDFF),
    val orange: Color = Color(0xFFE68A3F),
    val niceBlue: Color = Color(0xFFECF5F8),
    val nicePurple: Color = Color(0xFFF6EDF8),
    val niceBlack: Color = Color(0XFF292D32),
    val strokeGrey: Color = Color(0xFFD9D9D9),
    val oceanBlue: Color = Color(0xFF408AAA),
    val mediumGrey: Color = Color(0xFF727272),
    val totalDark: Color = Color(0xFF000000),
    val lightGrey: Color = Color(0xFFC1C1C1),
    val skyBlue: Color = Color(0xFFE8F9FF),
    val grey: Color = Color(0xFFD9D9D9),
    val lighterDark: Color = Color(0xFF1A1A1A),
    val purple: Color = Color(0xFF62006A),
    val almostWhite: Color = Color(0xFFF1F1F1),
) {
    /**
     * Just map these values to material 3 equivalent.
     */
    fun toMaterial3Colors(): ColorScheme {
        return lightColorScheme(
            primary = primaryLight,
            onPrimary = onPrimaryLight,
            primaryContainer = primaryContainerLight,
            onPrimaryContainer = onPrimaryContainerLight,
            secondary = secondaryLight,
            onSecondary = onSecondaryLight,
            secondaryContainer = secondaryContainerLight,
            onSecondaryContainer = onSecondaryContainerLight,
            tertiary = tertiaryLight,
            onTertiary = onTertiaryLight,
            tertiaryContainer = tertiaryContainerLight,
            onTertiaryContainer = onTertiaryContainerLight,
            error = errorLight,
            onError = onErrorLight,
            errorContainer = errorContainerLight,
            onErrorContainer = onErrorContainerLight,
            background = backgroundLight,
            onBackground = onBackgroundLight,
            surface = surfaceLight,
            onSurface = onSurfaceLight,
            surfaceVariant = surfaceVariantLight,
            onSurfaceVariant = onSurfaceVariantLight,
            outline = outlineLight,
            outlineVariant = outlineVariantLight,
            scrim = scrimLight,
            inverseSurface = inverseSurfaceLight,
            inverseOnSurface = inverseOnSurfaceLight,
            inversePrimary = inversePrimaryLight,
        )
    }
}
