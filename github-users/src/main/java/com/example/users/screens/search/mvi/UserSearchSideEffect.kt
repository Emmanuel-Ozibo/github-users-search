package com.example.users.screens.search.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.SideEffect
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.users.mappers.UserRemoteModelToUserMapper
import com.example.users.remote.repository.UserRepository
import com.example.users.screens.search.states.UserSearchState

class UserSearchSideEffect(
    private val repository: UserRepository,
    private val mapper: UserRemoteModelToUserMapper,
) : SideEffect<State<UserSearchState>, UserSearchAction> {
    override suspend fun invoke(
        dispatch: Dispatch<UserSearchAction>,
        state: State<UserSearchState>,
        action: UserSearchAction,
    ) {
        if (action is UserSearchAction.SearchUsers) {
            if (action.query.isEmpty()) {
                dispatch(UserSearchAction.EmptySearchQuery)
                return
            }

            dispatch(UserSearchAction.ShowLoading)

            when (val response = repository.searchUsers(query = action.query)) {
                is NetworkResponse.Failure -> {
                    dispatch(UserSearchAction.SearchUsersError(response.errorMessage))
                }
                is NetworkResponse.Success -> {
                    dispatch(
                        UserSearchAction.LoadedUsers(
                            users = response.response.map { mapper.to(it) },
                            query = action.query,
                        ),
                    )
                }
            }
        }
    }
}
