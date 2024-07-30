package com.example.repository.screens.mvi

import com.example.core.corestate.Reducer
import com.example.core.corestate.state.State
import com.example.core.corestate.state.State.Failed
import com.example.core.corestate.state.State.Loaded
import com.example.core.corestate.state.State.Loading
import com.example.core.corestate.state.State.Uninitialised
import com.example.repository.screens.states.RepositorySearchState

object RepositorySearchReducer {
    val reducer: Reducer<State<RepositorySearchState>, RepositorySearchAction> = {
            state, action ->

        when (action) {
            is RepositorySearchAction.LoadedRepositories -> {
                if (state is Loaded) {
                    Loaded(
                        data =
                            state.data.copy(
                                repositories = action.repositories,
                                query = action.query,
                                lastFetchedItemsCount = action.repositories.size,
                            ),
                    )
                } else {
                    Loaded(
                        data =
                            RepositorySearchState(
                                repositories = action.repositories,
                                query = action.query,
                                lastFetchedItemsCount = action.repositories.size,
                            ),
                    )
                }
            }
            is RepositorySearchAction.SearchRepositoriesError -> Failed(errorMessage = action.error)
            is RepositorySearchAction.SearchRepository -> Loading()
            RepositorySearchAction.ShowLoading -> Loading()
            RepositorySearchAction.EmptySearchQuery -> Uninitialised()

            RepositorySearchAction.LoadMore -> {
                if (state is Loaded) {
                    Loaded(
                        data =
                            state.data.copy(
                                isLoadingMore = true,
                                page = state.data.page + 1,
                            ),
                    )
                } else {
                    state
                }
            }
            RepositorySearchAction.LoadMoreError -> {
                if (state is Loaded) {
                    Loaded(
                        data =
                            state.data.copy(
                                isLoadingMore = false,
                                page = state.data.page - 1,
                            ),
                    )
                } else {
                    state
                }
            }
            is RepositorySearchAction.LoadMoreSuccess -> {
                if (state is Loaded) {
                    Loaded(
                        data =
                            state.data.copy(
                                isLoadingMore = false,
                                repositories = state.data.repositories + action.repositories,
                                lastFetchedItemsCount = action.repositories.size,
                            ),
                    )
                } else {
                    state
                }
            }
        }
    }
}
