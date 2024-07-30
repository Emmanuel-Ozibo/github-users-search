package com.example.repository.screens.states

import com.example.repository.models.Repository

const val DEFAULT_ITEMS_PER_PAGE = 10

data class RepositorySearchState(
    val query: String = "",
    val repositories: List<Repository> = emptyList(),
    val lastFetchedItemsCount: Int = 0,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
) {
    val hasNoRepository: Boolean
        get() {
            return repositories.isEmpty()
        }

    val mightHaveMoreItems: Boolean
        get() {
            return lastFetchedItemsCount == DEFAULT_ITEMS_PER_PAGE
        }
}
