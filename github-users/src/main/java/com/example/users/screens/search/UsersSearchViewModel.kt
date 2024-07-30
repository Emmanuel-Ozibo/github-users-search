package com.example.users.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.corestate.MVIStore
import com.example.core.corestate.state.State
import com.example.users.mappers.UserRemoteModelToUserMapper
import com.example.users.remote.repository.UserRepository
import com.example.users.screens.search.mvi.LoadMoreUsersSideEffect
import com.example.users.screens.search.mvi.UserSearchReducer
import com.example.users.screens.search.mvi.UserSearchSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersSearchViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        private val mapper: UserRemoteModelToUserMapper,
    ) : ViewModel() {
        val store by lazy {
            MVIStore(
                coroutineScope = viewModelScope,
                initialState = State.Uninitialised(),
                reducer = UserSearchReducer.reducer,
                sideEffects =
                    listOf(
                        UserSearchSideEffect(
                            repository = userRepository,
                            mapper = mapper,
                        ),
                        LoadMoreUsersSideEffect(
                            repository = userRepository,
                            mapper = mapper,
                        ),
                    ),
            )
        }
    }
