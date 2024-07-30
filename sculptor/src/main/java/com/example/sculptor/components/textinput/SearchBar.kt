package com.example.sculptor.components.textinput

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sculptor.R
import com.example.sculptor.SculptorTheme

val SEARCH_BUTTON_WIDTH = 120.dp
const val DEFAULT_ANIM_DURATION = 300

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    onSearchClick: (query: String) -> Unit,
) {
    var isVisible by remember { mutableStateOf(false) }

    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter =
            slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = DEFAULT_ANIM_DURATION),
            ) + fadeIn(animationSpec = tween(durationMillis = DEFAULT_ANIM_DURATION)),
        exit =
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = DEFAULT_ANIM_DURATION),
            ) + fadeOut(animationSpec = tween(durationMillis = DEFAULT_ANIM_DURATION)),
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            value = searchQuery,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                )
            },
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = SculptorTheme.colors.lightGrey,
                    focusedBorderColor = SculptorTheme.colors.lightGrey,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedLeadingIconColor = SculptorTheme.colors.lightGrey,
                    focusedLeadingIconColor = SculptorTheme.colors.lightGrey,
                    cursorColor = SculptorTheme.colors.niceBlack,
                ),
            onValueChange = {
                searchQuery = it
            },
            shape = RoundedCornerShape(SculptorTheme.space.half),
            placeholder = {
                Text(
                    text = placeholder,
                    style = SculptorTheme.typography.tiny,
                )
            },
            suffix = {
                SearchButton(
                    onClick = { onSearchClick(searchQuery) },
                )
            },
        )
    }
}

@Composable
fun SearchButton(onClick: () -> Unit) {
    Button(
        modifier =
            Modifier
                .width(SEARCH_BUTTON_WIDTH),
        shape = RoundedCornerShape(SculptorTheme.space.quarter),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = SculptorTheme.colors.niceBlack,
            ),
        onClick = onClick,
    ) {
        Text(
            text = "Search",
            style =
                SculptorTheme.typography.tinySemiBold.copy(
                    color = Color.White,
                ),
        )
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    SculptorTheme {
        SearchBar(
            placeholder = "Search",
            onSearchClick = {
            },
        )
    }
}
