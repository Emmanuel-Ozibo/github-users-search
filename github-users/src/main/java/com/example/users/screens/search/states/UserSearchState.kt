package com.example.users.screens.search.states

import com.example.users.screens.models.User

const val DEFAULT_ITEMS_PER_PAGE = 10

data class UserSearchState(
    val query: String = "",
    val users: List<User> = emptyList(),
    val lastFetchedItemsCount: Int = 0,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
) {
    val hasNoUsers: Boolean
        get() {
            return users.isEmpty()
        }

    val mightHaveMoreItems: Boolean
        get() {
            return lastFetchedItemsCount == DEFAULT_ITEMS_PER_PAGE
        }
}
