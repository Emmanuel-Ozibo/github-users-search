package com.example.sculptor.components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.sculptor.R
import com.example.sculptor.SculptorTheme

val fontFamily =
    FontFamily(
        Font(R.font.manrope_light, FontWeight.Light),
        Font(R.font.manrope_regular, FontWeight.Normal),
        Font(R.font.manrope_medium, FontWeight.Medium),
        Font(R.font.manrope_bold, FontWeight.Bold),
        Font(R.font.manrope_extrabold, FontWeight.ExtraBold),
    )

@Composable
fun TwoStyledText(
    modifier: Modifier = Modifier,
    firstText: String,
    secondText: String,
) {
    val annotatedString =
        buildAnnotatedString {
            withStyle(
                style =
                    SpanStyle(
                        color = SculptorTheme.colors.purple,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        fontFamily = fontFamily,
                    ),
            ) {
                append("$firstText/")
            }

            withStyle(
                style =
                    SpanStyle(
                        color = SculptorTheme.colors.totalDark,
                        fontWeight = FontWeight.W600,
                        fontSize = 12.sp,
                        fontFamily = fontFamily,
                    ),
            ) {
                append(secondText)
            }
        }

    Text(
        modifier = modifier,
        text = annotatedString,
    )
}

@Preview
@Composable
private fun TwoStyledTextPreview() {
    SculptorTheme {
        TwoStyledText(
            firstText = "Vundere",
            secondText = "Python",
        )
    }
}
