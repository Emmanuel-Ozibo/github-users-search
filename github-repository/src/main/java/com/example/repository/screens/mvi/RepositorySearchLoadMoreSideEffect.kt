package com.example.repository.screens.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.SideEffect
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.repository.mappers.RepositoryRemoteToRepositoryMapper
import com.example.repository.remote.repository.SearchRepository
import com.example.repository.screens.states.RepositorySearchState

class RepositorySearchLoadMoreSideEffect(
    private val repository: SearchRepository,
    private val mapper: RepositoryRemoteToRepositoryMapper,
) : SideEffect<State<RepositorySearchState>, RepositorySearchAction> {
    override suspend fun invoke(
        dispatch: Dispatch<RepositorySearchAction>,
        state: State<RepositorySearchState>,
        action: RepositorySearchAction,
    ) {
        if (action is RepositorySearchAction.LoadMore) {
            if (state is State.Loaded) {
                when (
                    val result =
                        repository.search(
                            query = state.data.query,
                            page = state.data.page,
                        )
                ) {
                    is NetworkResponse.Failure -> {
                        dispatch(RepositorySearchAction.LoadMoreError)
                    }

                    is NetworkResponse.Success -> {
                        dispatch(
                            RepositorySearchAction.LoadMoreSuccess(
                                repositories = result.response.map { mapper.to(it) },
                            ),
                        )
                    }
                }
            }
        }
    }
}
