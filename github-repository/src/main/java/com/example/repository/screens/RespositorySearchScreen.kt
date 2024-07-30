package com.example.repository.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.corestate.state.State
import com.example.repository.R
import com.example.repository.screens.mvi.RepositorySearchAction
import com.example.repository.screens.states.RepositorySearchState
import com.example.repository.screens.widgets.RepositoryItemWidget
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.EmptyView
import com.example.sculptor.components.lists.PaginatedLazyColumn
import com.example.sculptor.components.textinput.SearchBar

@Composable
fun RepositorySearchScreenWrapper(viewModel: RepositorySearchViewModel = hiltViewModel()) {
    val states by viewModel.store.state.collectAsStateWithLifecycle()

    RepositorySearchScreen(
        state = states,
        onSearch = { query: String ->
            viewModel.store.dispatch(
                RepositorySearchAction.SearchRepository(query),
            )
        },
        onLoadMore = {
            viewModel.store.dispatch(RepositorySearchAction.LoadMore)
        },
    )
}

@Composable
fun RepositorySearchScreen(
    state: State<RepositorySearchState>,
    onSearch: (query: String) -> Unit,
    onLoadMore: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(SculptorTheme.space.one),
    ) {
        SearchBar(
            placeholder = stringResource(R.string.search_bar_placeholder_text),
            onSearchClick = onSearch,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
            verticalArrangement =
                Arrangement.spacedBy(
                    SculptorTheme.space.one,
                    Alignment.CenterVertically,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (state is State.Uninitialised) {
                EmptyView(message = stringResource(id = R.string.empty_search_bar))
            }

            if (state is State.Loading) {
                CircularProgressIndicator(
                    color = SculptorTheme.colors.niceBlack,
                )
            }

            if (state is State.Failed) {
                EmptyView(message = state.errorMessage)
            }

            if (state is State.Loaded) {
                LoadedListContent(state = state.data, onLoadMore = onLoadMore)
            }
        }
    }
}

@Composable
private fun ColumnScope.LoadedListContent(
    state: RepositorySearchState,
    onLoadMore: () -> Unit,
) {
    PaginatedLazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .weight(1f),
        onLoadMore = {
            if (state.mightHaveMoreItems) {
                onLoadMore()
            }
        },
        showLoader = state.isLoadingMore,
        loading = {
            CircularProgressIndicator(
                color = SculptorTheme.colors.niceBlack,
            )
        },
    ) {
        if (state.hasNoRepository) {
            item {
                EmptyView(message = stringResource(id = R.string.empty_repository_result_message))
            }
        }

        items(
            count = state.repositories.size,
            key = { index -> state.repositories[index].id },
        ) { index ->
            val repository = state.repositories[index]

            RepositoryItemWidget(
                imageUrl = repository.owner.avatarUrl,
                repositoryName = repository.fullName,
                language = repository.language,
                description = repository.description,
                tags = listOf("Issues: ${repository.openIssuesCount}"),
                numberOfStars = repository.stargazersCount,
            )
        }
    }
}
