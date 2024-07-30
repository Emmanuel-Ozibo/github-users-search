package com.example.users.screens.search.mvi

import com.example.core.corestate.Reducer
import com.example.core.corestate.state.State
import com.example.core.corestate.state.State.Failed
import com.example.core.corestate.state.State.Loaded
import com.example.core.corestate.state.State.Loading
import com.example.core.corestate.state.State.Uninitialised
import com.example.users.screens.search.states.UserSearchState

object UserSearchReducer {
    val reducer: Reducer<State<UserSearchState>, UserSearchAction> = {
            state, action ->

        when (action) {
            UserSearchAction.EmptySearchQuery -> Uninitialised()
            is UserSearchAction.LoadedUsers -> {
                if (state is Loaded) {
                    Loaded(
                        data =
                            state.data.copy(
                                users = action.users,
                                lastFetchedItemsCount = action.users.size,
                                query = action.query,
                                page = 1,
                            ),
                    )
                } else {
                    Loaded(
                        data =
                            UserSearchState(
                                users = action.users,
                                lastFetchedItemsCount = action.users.size,
                                query = action.query,
                            ),
                    )
                }
            }
            is UserSearchAction.SearchUsers -> Loading()
            is UserSearchAction.SearchUsersError -> Failed(errorMessage = action.error)
            UserSearchAction.ShowLoading -> Loading()
            UserSearchAction.LoadMoreUsers -> {
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
            is UserSearchAction.LoadMoreUsersError -> state
            is UserSearchAction.LoadMoreUsersSuccessful -> {
                if (state is Loaded) {
                    Loaded(
                        data =
                            state.data.copy(
                                isLoadingMore = false,
                                users = state.data.users + action.users,
                                lastFetchedItemsCount = action.users.size,
                            ),
                    )
                } else {
                    state
                }
            }
        }
    }
}
