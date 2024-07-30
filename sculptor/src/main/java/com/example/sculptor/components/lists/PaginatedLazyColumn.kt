package com.example.sculptor.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme

@Composable
fun PaginatedLazyColumn(
    modifier: Modifier = Modifier,
    loading: @Composable () -> Unit = {},
    showLoader: Boolean = false,
    onLoadMore: () -> Unit,
    content: LazyListScope.() -> Unit,
) {
    val listState = rememberLazyListState()

    val isAtEnd by remember {
        derivedStateOf {
            listState.canScrollForward
            val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            if (visibleItemsInfo.isNotEmpty()) {
                // Check if the last visible item is the last item in the list
                visibleItemsInfo.last().index == totalItemsCount - 1
            } else {
                false
            }
        }
    }

    LaunchedEffect(isAtEnd) {
        if (isAtEnd) {
            onLoadMore()
        }
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement =
            Arrangement.spacedBy(SculptorTheme.space.one),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState,
    ) {
        content()

        item {
            if (showLoader) {
                loading()
            }
        }
    }
}

@Preview
@Composable
private fun PaginatedLazyColumnPreview() {
    SculptorTheme {
        PaginatedLazyColumn(
            onLoadMore = {},
        ) {
        }
    }
}
