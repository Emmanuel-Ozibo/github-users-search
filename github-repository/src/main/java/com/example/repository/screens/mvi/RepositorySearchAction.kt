package com.example.repository.screens.mvi

import com.example.repository.models.Repository

sealed interface RepositorySearchAction {
    data object ShowLoading : RepositorySearchAction

    data class SearchRepository(val query: String) : RepositorySearchAction

    data class LoadedRepositories(val query: String, val repositories: List<Repository>) : RepositorySearchAction

    data class SearchRepositoriesError(val error: String) : RepositorySearchAction

    data object EmptySearchQuery : RepositorySearchAction

    data object LoadMore : RepositorySearchAction

    data object LoadMoreError : RepositorySearchAction

    data class LoadMoreSuccess(val repositories: List<Repository>) : RepositorySearchAction
}
