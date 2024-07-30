package com.example.users.screens.search.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.SideEffect
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.users.mappers.UserRemoteModelToUserMapper
import com.example.users.remote.repository.UserRepository
import com.example.users.screens.search.states.UserSearchState

class LoadMoreUsersSideEffect(
    private val repository: UserRepository,
    private val mapper: UserRemoteModelToUserMapper,
) : SideEffect<State<UserSearchState>, UserSearchAction> {
    override suspend fun invoke(
        dispatch: Dispatch<UserSearchAction>,
        state: State<UserSearchState>,
        action: UserSearchAction,
    ) {
        if (action is UserSearchAction.LoadMoreUsers) {
            if (state is State.Loaded) {
                when (
                    val response =
                        repository.searchUsers(
                            query = state.data.query,
                            page = state.data.page,
                        )
                ) {
                    is NetworkResponse.Failure -> {
                        dispatch(UserSearchAction.LoadMoreUsersError(response.errorMessage))
                    }
                    is NetworkResponse.Success -> {
                        dispatch(
                            UserSearchAction.LoadMoreUsersSuccessful(
                                users = response.response.map { mapper.to(it) },
                                query = state.data.query,
                            ),
                        )
                    }
                }
            }
        }
    }
}
