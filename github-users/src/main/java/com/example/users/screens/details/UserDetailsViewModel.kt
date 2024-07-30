package com.example.users.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.corestate.MVIStore
import com.example.core.corestate.state.State
import com.example.users.mappers.UserRepositoryRemoteModelToUserRepositoryModelMapper
import com.example.users.remote.repository.UserRepository
import com.example.users.screens.details.mvi.LoadUserRepositoriesSideEffect
import com.example.users.screens.details.mvi.UserDetailReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        private val mapper: UserRepositoryRemoteModelToUserRepositoryModelMapper,
    ) : ViewModel() {
        val store by lazy {
            MVIStore(
                coroutineScope = viewModelScope,
                initialState = State.Uninitialised(),
                reducer = UserDetailReducer.reducer,
                sideEffects =
                    listOf(
                        LoadUserRepositoriesSideEffect(
                            repository = userRepository,
                            mapper = mapper,
                        ),
                    ),
            )
        }
    }
