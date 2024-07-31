package com.example.users.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
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
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.EmptyView
import com.example.sculptor.components.lists.PaginatedLazyColumn
import com.example.sculptor.components.textinput.SearchBar
import com.example.users.R
import com.example.users.screens.models.User
import com.example.users.screens.search.mvi.UserSearchAction
import com.example.users.screens.search.states.UserSearchState
import com.example.users.widget.UserItemCard

val LOADING_INDICATOR_SIZE = 50.dp

@Composable
fun UserSearchScreenWrapper(
    viewModel: UsersSearchViewModel = hiltViewModel(),
    onUserItemCardClick: (user: User) -> Unit,
) {
    val states by viewModel.store.state.collectAsStateWithLifecycle()

    UserSearchScreen(
        state = states,
        onUserItemCardClick = onUserItemCardClick,
        onSearchClick = { query ->
            viewModel.store.dispatch(
                UserSearchAction.SearchUsers(query),
            )
        },
        onLoadMoreUsers = {
            viewModel.store.dispatch(UserSearchAction.LoadMoreUsers)
        },
    )
}

@Composable
fun UserSearchScreen(
    state: State<UserSearchState>,
    onUserItemCardClick: (user: User) -> Unit,
    onSearchClick: (query: String) -> Unit,
    onLoadMoreUsers: () -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(SculptorTheme.space.one),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar(
            placeholder = stringResource(id = R.string.github_users_search_bar_placeholder_text),
            onSearchClick = onSearchClick,
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (state is State.Uninitialised) {
            Box(modifier = Modifier.weight(1f)) {
                DefaultEmptyUIState(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }

        if (state is State.Loading) {
            Box(modifier = Modifier.weight(1f)) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = SculptorTheme.colors.niceBlack,
                )
            }
        }

        if (state is State.Loaded) {
            PaginatedLazyColumn(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .weight(1f),
                onLoadMore = onLoadMoreUsers,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.height(LOADING_INDICATOR_SIZE),
                        color = SculptorTheme.colors.niceBlack,
                    )
                },
            ) {
                loadedUIState(
                    state = state.data,
                    onUserItemCardClick = onUserItemCardClick,
                )
            }
        }
    }
}

@Composable
fun DefaultEmptyUIState(modifier: Modifier = Modifier) {
    EmptyView(
        modifier = modifier,
        message = stringResource(id = R.string.github_users_empty_search_query_message),
        textColor = SculptorTheme.colors.totalDark,
    )
}

fun LazyListScope.loadedUIState(
    state: UserSearchState,
    onUserItemCardClick: (user: User) -> Unit,
) {
    if (state.hasNoUsers) {
        item {
            EmptyView(
                message = stringResource(id = R.string.github_users_empty_search_result_message),
                textColor = SculptorTheme.colors.totalDark,
            )
        }
    }

    items(
        count = state.users.size,
        key = { index -> state.users[index].id },
    ) { index ->
        val user = state.users[index]
        UserItemCard(
            imageUrl = user.avatar,
            fullName = user.name,
            profileDescription = user.bio,
            location = user.location,
            email = user.email,
            tag = user.login,
            onCardClick = { onUserItemCardClick(user) },
        )
    }
}
