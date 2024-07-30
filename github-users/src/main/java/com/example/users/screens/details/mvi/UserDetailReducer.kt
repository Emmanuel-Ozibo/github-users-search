package com.example.users.screens.details.mvi

import com.example.core.corestate.Reducer
import com.example.core.corestate.state.State
import com.example.users.screens.details.states.UserDetailsState

object UserDetailReducer {
    val reducer: Reducer<State<UserDetailsState>, UserDetailsAction> = {
            state, action ->

        when (action) {
            UserDetailsAction.ShowLoading -> State.Loading()
            is UserDetailsAction.LoadedUserRepository -> {
                if (state is State.Loaded) {
                    State.Loaded(
                        data =
                            state.data.copy(
                                repositories = action.repositories,
                            ),
                    )
                } else {
                    State.Loaded(
                        data =
                            UserDetailsState(
                                repositories = action.repositories,
                            ),
                    )
                }
            }
            is UserDetailsAction.LoadUserRepositoryError -> State.Failed(errorMessage = action.error)

            is UserDetailsAction.LoadUserRepositories -> State.Loading()
        }
    }
}
