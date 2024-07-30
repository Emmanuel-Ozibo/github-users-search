package com.example.users.screens.details.mvi

import com.example.users.screens.models.UserRepositoryModel

sealed interface UserDetailsAction {
    data class LoadUserRepositories(val userName: String) : UserDetailsAction

    data object ShowLoading : UserDetailsAction

    data class LoadedUserRepository(val repositories: List<UserRepositoryModel>) : UserDetailsAction

    data class LoadUserRepositoryError(val error: String) : UserDetailsAction
}
