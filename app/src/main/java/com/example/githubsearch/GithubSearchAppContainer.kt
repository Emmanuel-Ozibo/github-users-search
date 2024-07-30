package com.example.githubsearch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.githubsearch.screens.HomeScreen
import com.example.repository.screens.RepositorySearchScreenWrapper
import com.example.sculptor.components.nav.GSBottomNavigation
import com.example.sculptor.components.nav.NavBarDestination.Home
import com.example.sculptor.components.nav.NavBarDestination.RepositorySearch
import com.example.sculptor.components.nav.NavBarDestination.UsersSearch
import com.example.users.screens.UserDestination
import com.example.users.screens.details.UserDetailScreen
import com.example.users.screens.models.User
import com.example.users.screens.search.UserSearchScreenWrapper
import com.google.gson.Gson

@Suppress("LongMethod")
@Composable
internal fun GithubSearchAppContainer() {
    val navController = rememberNavController()

    val navItems = listOf(Home, RepositorySearch, UsersSearch)

    var selectedItem by remember { mutableStateOf(navItems.first()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            GSBottomNavigation(
                navItems = navItems,
                selectedItem = selectedItem,
                onNavItemClick = { destination ->
                    selectedItem = destination
                    navController.navigate(destination)
                },
            )
        },
    ) { padding ->

        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Home,
        ) {
            composable<Home> {
                HomeScreen(
                    onUsersCardClick = {
                        selectedItem = navItems.first { it == UsersSearch }
                        navController.navigate(UsersSearch)
                    },
                    onRepositoriesCardClick = {
                        selectedItem = navItems.first { it == RepositorySearch }
                        navController.navigate(RepositorySearch)
                    },
                )
            }

            composable<RepositorySearch> {
                RepositorySearchScreenWrapper()
            }

            navigation<UsersSearch>(startDestination = UserDestination.UserSearch) {
                composable<UserDestination.UserSearch> {
                    UserSearchScreenWrapper(
                        onUserItemCardClick = {
                            val userJson = Gson().toJson(it)
                            navController.navigate(UserDestination.UserDetails(userJson = userJson))
                        },
                    )
                }

                composable<UserDestination.UserDetails> { entry ->
                    val userJson = entry.toRoute<UserDestination.UserDetails>().userJson

                    val user = Gson().fromJson(userJson, User::class.java)

                    UserDetailScreen(
                        user = user,
                        onBackClick = {
                            navController.popBackStack()
                        },
                    )
                }
            }
        }
    }
}
