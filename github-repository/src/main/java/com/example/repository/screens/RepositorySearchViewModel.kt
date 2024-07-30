package com.example.repository.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.corestate.MVIStore
import com.example.core.corestate.state.State.Uninitialised
import com.example.repository.mappers.RepositoryRemoteToRepositoryMapper
import com.example.repository.remote.repository.SearchRepository
import com.example.repository.screens.mvi.RepositorySearchLoadMoreSideEffect
import com.example.repository.screens.mvi.RepositorySearchReducer
import com.example.repository.screens.mvi.RepositorySearchSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositorySearchViewModel
    @Inject
    constructor(
        private val repository: SearchRepository,
        private val mapper: RepositoryRemoteToRepositoryMapper,
    ) : ViewModel() {
        val store by lazy {
            MVIStore(
                coroutineScope = viewModelScope,
                initialState = Uninitialised(),
                reducer = RepositorySearchReducer.reducer,
                sideEffects =
                    listOf(
                        RepositorySearchSideEffect(
                            repository = repository,
                            mapper = mapper,
                        ),
                        RepositorySearchLoadMoreSideEffect(
                            repository = repository,
                            mapper = mapper,
                        ),
                    ),
            )
        }
    }
