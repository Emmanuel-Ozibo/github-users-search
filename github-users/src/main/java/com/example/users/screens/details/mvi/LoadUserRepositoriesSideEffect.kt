package com.example.users.screens.details.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.SideEffect
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.users.mappers.UserRepositoryRemoteModelToUserRepositoryModelMapper
import com.example.users.remote.repository.UserRepository
import com.example.users.screens.details.states.UserDetailsState

class LoadUserRepositoriesSideEffect(
    private val repository: UserRepository,
    private val mapper: UserRepositoryRemoteModelToUserRepositoryModelMapper,
) : SideEffect<State<UserDetailsState>, UserDetailsAction> {
    override suspend fun invoke(
        dispatch: Dispatch<UserDetailsAction>,
        state: State<UserDetailsState>,
        action: UserDetailsAction,
    ) {
        if (action is UserDetailsAction.LoadUserRepositories) {
            dispatch(UserDetailsAction.ShowLoading)

            when (val result = repository.getUserRepositories(action.userName)) {
                is NetworkResponse.Failure -> {
                    dispatch(UserDetailsAction.LoadUserRepositoryError(result.errorMessage))
                }
                is NetworkResponse.Success -> {
                    dispatch(
                        UserDetailsAction.LoadedUserRepository(
                            repositories = result.response.map { mapper.to(it) },
                        ),
                    )
                }
            }
        }
    }
}
