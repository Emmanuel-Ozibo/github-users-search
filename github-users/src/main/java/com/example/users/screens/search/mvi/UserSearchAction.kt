package com.example.users.screens.search.mvi

import com.example.users.screens.models.User

sealed interface UserSearchAction {
    data object ShowLoading : UserSearchAction

    data class SearchUsers(val query: String) : UserSearchAction

    data class LoadedUsers(val users: List<User>, val query: String) : UserSearchAction

    data class SearchUsersError(val error: String) : UserSearchAction

    data object EmptySearchQuery : UserSearchAction

    data object LoadMoreUsers : UserSearchAction

    data class LoadMoreUsersSuccessful(val users: List<User>, val query: String) : UserSearchAction

    data class LoadMoreUsersError(val error: String) : UserSearchAction
}
