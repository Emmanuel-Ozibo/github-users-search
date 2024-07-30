package com.example.users.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.corestate.state.State
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.EmptyView
import com.example.sculptor.components.appbars.ActionTopAppBar
import com.example.sculptor.components.texts.CountsTextView
import com.example.users.R
import com.example.users.screens.details.mvi.UserDetailsAction
import com.example.users.screens.details.states.UserDetailsState
import com.example.users.screens.models.User
import com.example.users.screens.models.UserRepositoryModel
import com.example.users.widget.FollowersAndFollowing
import com.example.users.widget.LocationLink
import com.example.users.widget.ProfilePictureNameTag
import com.example.users.widget.UsersRepositoryItemCard

@Composable
fun UserDetailScreen(
    user: User,
    onBackClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollState(),
                ),
    ) {
        ActionTopAppBar(
            title = stringResource(id = R.string.github_users_top_app_bar_text),
            onBackClick = onBackClick,
        )

        Spacer(modifier = Modifier.height(SculptorTheme.space.one))

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(horizontal = SculptorTheme.space.one),
        ) {
            BasicProfileInformation(user = user)

            Spacer(modifier = Modifier.height(SculptorTheme.space.one))

            UserRepositoryWrapper(username = user.login)
        }
    }
}

@Composable
fun UserRepositoryList(repositories: List<UserRepositoryModel> = listOf()) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SculptorTheme.space.half),
    ) {
        if (repositories.isEmpty()) {
            item {
                EmptyView(
                    icon = R.drawable.ic_empty_two,
                    message = stringResource(id = R.string.github_users_empty_repositories_text),
                )
            }
        }

        if (repositories.isNotEmpty()) {
            items(
                count = repositories.size,
                key = { index -> repositories[index].id },
            ) { index ->
                val repository = repositories[index]
                UsersRepositoryItemCard(
                    description = repository.description,
                    forkedFrom = "Forked from discordify",
                    updatedAt =
                        stringResource(id = R.string.github_users_last_updated_text, repository.updatedAt),
                    language = repository.language,
                    name = repository.name,
                    repo = repository.language,
                    numberOfStars = "${repository.stargazersCount}",
                    visibility = repository.visibility,
                    onCardClick = {},
                )
            }
        }
    }
}

@Composable
fun UserRepositoryWrapper(
    viewModel: UserDetailsViewModel = hiltViewModel(),
    username: String,
) {
    val states by viewModel.store.state.collectAsStateWithLifecycle()

    LaunchedEffect(username) {
        viewModel.store.dispatch(UserDetailsAction.LoadUserRepositories(username))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (states is State.Loading || states is State.Uninitialised) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                CircularProgressIndicator(
                    modifier =
                        Modifier
                            .height(50.dp)
                            .align(Alignment.Center),
                    color = SculptorTheme.colors.niceBlack,
                )
            }
        }

        if (states is State.Failed) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                EmptyView(
                    modifier = Modifier.align(Alignment.Center),
                    icon = R.drawable.ic_empty_two,
                    message = (states as State.Failed<UserDetailsState>).errorMessage,
                )
            }
        }

        if (states is State.Loaded<UserDetailsState>) {
            UserRepositoryList(
                repositories = (states as State.Loaded<UserDetailsState>).data.repositories,
            )
        }
    }
}

@Composable
private fun ColumnScope.BasicProfileInformation(user: User) {
    ProfilePictureNameTag(
        fullName = user.name,
        tag = user.twitterUsername,
        imageUrl = user.avatar,
    )

    Spacer(modifier = Modifier.height(SculptorTheme.space.one))

    Text(
        text = user.bio,
        style = SculptorTheme.typography.labelRegular,
    )

    Spacer(modifier = Modifier.height(SculptorTheme.space.one))

    LocationLink(location = user.location, link = user.blog)

    Spacer(modifier = Modifier.height(SculptorTheme.space.half))

    FollowersAndFollowing(
        followers = user.followers,
        following = user.following,
    )

    Spacer(modifier = Modifier.height(SculptorTheme.space.two))

    CountsTextView(
        title = stringResource(id = R.string.github_users_repo_counts_text),
        count = user.publicRepos,
    )
}

@Preview
@Composable
private fun UserDetailScreenPreview() {
    SculptorTheme {
        UserDetailScreen(
            user =
                User(
                    id = 1,
                    login = "DynamicWebPaige",
                    name = "Paige Brown",
                    avatar = "https://avatars.githubusercontent.com/u/1?v=4",
                    company = "DynamicWeb",
                    blog = "https://dynamicweb.netlify.app",
                    location = "Lagos, Nigeria",
                    email = "paige@paige.com",
                    bio = "This is a random bio, which will be replace with actual content",
                    twitterUsername = "DynamicWebPaige",
                    publicRepos = 200,
                    followers = 100,
                    following = 100,
                ),
            onBackClick = {},
        )
    }
}
