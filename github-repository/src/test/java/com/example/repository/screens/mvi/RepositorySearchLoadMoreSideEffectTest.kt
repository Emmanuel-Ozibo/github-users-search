package com.example.repository.screens.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.repository.mappers.OwnerRemoteModelToOwnerMapper
import com.example.repository.mappers.RepositoryRemoteToRepositoryMapper
import com.example.repository.remote.repository.SearchRepository
import com.example.repository.screens.states.RepositorySearchState
import com.example.repository.utils.FakeDataGenerator
import com.example.repository.utils.runSideEffects
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RepositorySearchLoadMoreSideEffectTest {
    private val ownerMapper = OwnerRemoteModelToOwnerMapper()
    private val mapper = RepositoryRemoteToRepositoryMapper(ownerMapper)
    private val searchRepository = mockk<SearchRepository>()

    private val repositorySearchLoadMoreSideEffect =
        RepositorySearchLoadMoreSideEffect(
            repository = searchRepository,
            mapper = mapper,
        )

    @Test
    fun `verify that when LoadMore action is called and is successful LoadMoreSuccess action is dispatched`() =
        runTest {
            val calledActions: MutableList<RepositorySearchAction> = mutableListOf()
            val dispatch: Dispatch<RepositorySearchAction> = {
                    action: RepositorySearchAction ->
                calledActions.add(action)
            }

            coEvery { searchRepository.search(any()) } returns
                NetworkResponse.Success(FakeDataGenerator.getRemoteRepositories())

            runSideEffects(
                dispatch = dispatch,
                event = RepositorySearchAction.LoadMore,
                state = State.Loaded(data = RepositorySearchState(query = "react")),
                sideEffects = listOf(repositorySearchLoadMoreSideEffect),
            )

            assertEquals(1, calledActions.size)
            assertTrue(
                calledActions.contains(
                    RepositorySearchAction.LoadMoreSuccess(FakeDataGenerator.getRepositoryUiModel()),
                ),
            )
        }

    @Test
    fun `verify that when LoadMore action is called and is not successful LoadMoreError state is dispatched`() =
        runTest {
            val calledActions: MutableList<RepositorySearchAction> = mutableListOf()
            val dispatch: Dispatch<RepositorySearchAction> = {
                    action: RepositorySearchAction ->
                calledActions.add(action)
            }

            coEvery { searchRepository.search(any()) } returns
                NetworkResponse.Failure(errorMessage = "Test Error")

            runSideEffects(
                dispatch = dispatch,
                event = RepositorySearchAction.LoadMore,
                state = State.Loaded(data = RepositorySearchState(query = "react")),
                sideEffects = listOf(repositorySearchLoadMoreSideEffect),
            )

            assertEquals(1, calledActions.size)
            assertTrue(calledActions.contains(RepositorySearchAction.LoadMoreError))
        }
}
