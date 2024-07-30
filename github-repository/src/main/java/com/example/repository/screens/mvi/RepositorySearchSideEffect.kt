package com.example.repository.screens.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.SideEffect
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.repository.mappers.RepositoryRemoteToRepositoryMapper
import com.example.repository.remote.repository.SearchRepository
import com.example.repository.screens.states.RepositorySearchState

class RepositorySearchSideEffect(
    private val repository: SearchRepository,
    private val mapper: RepositoryRemoteToRepositoryMapper,
) : SideEffect<State<RepositorySearchState>, RepositorySearchAction> {
    override suspend fun invoke(
        dispatch: Dispatch<RepositorySearchAction>,
        state: State<RepositorySearchState>,
        action: RepositorySearchAction,
    ) {
        if (action is RepositorySearchAction.SearchRepository) {
            if (action.query.isEmpty()) {
                dispatch(RepositorySearchAction.EmptySearchQuery)
                return
            }

            dispatch(RepositorySearchAction.ShowLoading)

            when (val response = repository.search(query = action.query)) {
                is NetworkResponse.Failure -> {
                    dispatch(RepositorySearchAction.SearchRepositoriesError(response.errorMessage))
                }

                is NetworkResponse.Success -> {
                    dispatch(
                        RepositorySearchAction.LoadedRepositories(
                            repositories =
                                response.response.map {
                                    mapper.to(it)
                                },
                            query = action.query,
                        ),
                    )
                }
            }
        }
    }
}
