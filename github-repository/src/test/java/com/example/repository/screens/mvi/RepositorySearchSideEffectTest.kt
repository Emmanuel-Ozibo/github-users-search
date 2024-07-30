package com.example.repository.screens.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.state.State.Uninitialised
import com.example.core.utils.NetworkResponse
import com.example.repository.mappers.OwnerRemoteModelToOwnerMapper
import com.example.repository.mappers.RepositoryRemoteToRepositoryMapper
import com.example.repository.remote.repository.SearchRepository
import com.example.repository.utils.FakeDataGenerator
import com.example.repository.utils.runSideEffects
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RepositorySearchSideEffectTest {
    private val ownerMapper = OwnerRemoteModelToOwnerMapper()
    private val mapper = RepositoryRemoteToRepositoryMapper(ownerMapper = ownerMapper)
    private val searchRepository = mockk<SearchRepository>()

    private val repositorySearchSideEffect =
        RepositorySearchSideEffect(
            repository = searchRepository,
            mapper = mapper,
        )

    @Test
    fun `verify that when SearchRepository action is called with a query and it's successful LoadedRepositories is action is dispatched`() =
        runTest {
            val calledActions: MutableList<RepositorySearchAction> = mutableListOf()
            val dispatch: Dispatch<RepositorySearchAction> =
                { action: RepositorySearchAction -> calledActions.add(action) }

            coEvery { searchRepository.search(any()) } returns
                NetworkResponse.Success(FakeDataGenerator.getRemoteRepositories())

            runSideEffects(
                dispatch = dispatch,
                event = RepositorySearchAction.SearchRepository("react"),
                state = Uninitialised(),
                sideEffects = listOf(repositorySearchSideEffect),
            )

            assertTrue(calledActions.contains(RepositorySearchAction.ShowLoading))
            assertTrue(
                calledActions.contains(
                    RepositorySearchAction.LoadedRepositories(query = "react", FakeDataGenerator.getRepositoryUiModel()),
                ),
            )

            val loaded = calledActions.find { it is RepositorySearchAction.LoadedRepositories }
            assertEquals(
                FakeDataGenerator.getRepositoryUiModel(),
                (loaded as RepositorySearchAction.LoadedRepositories).repositories,
            )
        }

    @Test
    fun `verify that when SearchRepository action is called without a query EmptySearchQuery is dispatched`() =
        runTest {
            val calledActions: MutableList<RepositorySearchAction> = mutableListOf()
            val dispatch: Dispatch<RepositorySearchAction> =
                { action: RepositorySearchAction -> calledActions.add(action) }

            coEvery { searchRepository.search(any()) } returns
                NetworkResponse.Failure(errorMessage = "Test error message")

            runSideEffects(
                dispatch = dispatch,
                event = RepositorySearchAction.SearchRepository(""),
                state = Uninitialised(),
                sideEffects = listOf(repositorySearchSideEffect),
            )

            assertEquals(1, calledActions.size)
            assertTrue(calledActions.contains(RepositorySearchAction.EmptySearchQuery))
        }

    @Test
    fun `verify that when SearchRepository action is called with a query but not successful LoadedRepositories is action is dispatched`() =
        runTest {
            val calledActions: MutableList<RepositorySearchAction> = mutableListOf()
            val dispatch: Dispatch<RepositorySearchAction> =
                { action: RepositorySearchAction -> calledActions.add(action) }

            coEvery { searchRepository.search(any()) } returns
                NetworkResponse.Failure(errorMessage = "Test error message")

            runSideEffects(
                dispatch = dispatch,
                event = RepositorySearchAction.SearchRepository("react"),
                state = Uninitialised(),
                sideEffects = listOf(repositorySearchSideEffect),
            )

            assertTrue(calledActions.contains(RepositorySearchAction.ShowLoading))
            assertTrue(calledActions.contains(RepositorySearchAction.SearchRepositoriesError("Test error message")))
        }
}
