package com.example.sculptor.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sculptor.R

val fontFamily =
    FontFamily(
        Font(R.font.manrope_light, FontWeight.Light),
        Font(R.font.manrope_regular, FontWeight.Normal),
        Font(R.font.manrope_medium, FontWeight.Medium),
        Font(R.font.manrope_bold, FontWeight.Bold),
        Font(R.font.manrope_extrabold, FontWeight.ExtraBold),
    )

@Suppress("MagicNumber")
data class SCPTypography(
    val heading: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 40.sp,
            color = Color(0xFF000000),
        ),
    val medium: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.W700,
            color = Color(0xFF000000),
        ),
    val veryTiny: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 8.sp,
            fontWeight = FontWeight.W400,
            color = Color(0xFF000000),
        ),
    val veryTinySemiBold: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 8.sp,
            fontWeight = FontWeight.W600,
            color = Color(0xFF000000),
        ),
    val tiny: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 10.sp,
            fontWeight = FontWeight.W400,
            color = Color(0xFF000000),
        ),
    val tinySemiBold: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 10.sp,
            fontWeight = FontWeight.W600,
            color = Color(0xFF000000),
        ),
    val labelRegular: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 20.sp,
            color = Color(0xFF000000),
        ),
    val labelThin: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 20.sp,
            color = Color(0xFF000000),
        ),
    val labelBold: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 20.sp,
            color = Color(0xFF000000),
        ),
    val regular: TextStyle =
        TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 20.sp,
            color = Color(0xFF000000),
        ),
) {
    /**
     * Just map these values to material 3 equivalent.
     */
    fun toMaterial3Typography(): Typography {
        return Typography()
    }
}
