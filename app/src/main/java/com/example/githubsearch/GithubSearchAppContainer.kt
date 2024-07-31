package com.example.githubsearch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sculptor.components.nav.NavBarDestination.UsersSearch
import com.example.users.screens.UserDestination
import com.example.users.screens.details.UserDetailScreen
import com.example.users.screens.models.User
import com.example.users.screens.search.UserSearchScreenWrapper
import com.google.gson.Gson

@Composable
internal fun GithubSearchAppContainer() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->

        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = UsersSearch,
        ) {
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
