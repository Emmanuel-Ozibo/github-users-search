package com.example.repository.screens.mvi

import com.example.core.corestate.state.State
import com.example.repository.models.Repository
import com.example.repository.screens.states.RepositorySearchState
import com.example.repository.utils.FakeDataGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RepositorySearchReducerTest {
    private val reducer = RepositorySearchReducer.reducer

    @Test
    fun `verify that when ShowLoading is called then Loading State is returned`() {
        val newState =
            reducer(
                State.Uninitialised(),
                RepositorySearchAction.ShowLoading,
            )

        assertTrue(newState is State.Loading)
    }

    @Test
    fun `verify that when EmptySearchQuery action is called then Uninitialised State is returned`() {
        val newState =
            reducer(
                State.Uninitialised(),
                RepositorySearchAction.EmptySearchQuery,
            )

        assertTrue(newState is State.Uninitialised)
    }

    @Test
    fun `verify that when SearchRepository action is called then Loading State is returned`() {
        val newState =
            reducer(
                State.Uninitialised(),
                RepositorySearchAction.SearchRepository(query = "any"),
            )

        assertTrue(newState is State.Loading)
    }

    @Test
    fun `verify that when SearchRepositoriesError is called then Failed state is returned`() {
        val newState =
            reducer(
                State.Loading(),
                RepositorySearchAction.SearchRepositoriesError(error = "test error message"),
            )

        assertTrue(newState is State.Failed)
    }

    @Test
    fun `verify that when LoadedRepositories action is called then Loaded is returned`() {
        val newState =
            reducer(
                State.Loading(),
                RepositorySearchAction.LoadedRepositories(
                    query = "react",
                    repositories = listOf(),
                ),
            )

        assertTrue(newState is State.Loaded)

        assertEquals(
            (newState as State.Loaded).data.repositories,
            listOf<Repository>(),
        )
    }

    @Test
    fun `verify that when LoadMore action is called then Loaded is returned with new load more state`() {
        val initialState =
            RepositorySearchState(
                repositories = FakeDataGenerator.getRepositoryUiModel(),
                query = "react",
            )

        val newState =
            reducer(
                State.Loaded(data = initialState),
                RepositorySearchAction.LoadMore,
            )

        assertTrue(newState is State.Loaded)
        assertEquals(true, (newState as State.Loaded).data.isLoadingMore)
        assertEquals(2, newState.data.page)
    }

    @Test
    fun `verify that when LoadMoreError action is called then Loaded is returned with new load more state`() {
        val initialState =
            RepositorySearchState(
                repositories = FakeDataGenerator.getRepositoryUiModel(),
                query = "react",
                page = 2,
            )

        val newState =
            reducer(
                State.Loaded(data = initialState),
                RepositorySearchAction.LoadMoreError,
            )

        assertTrue(newState is State.Loaded)
        assertEquals(false, (newState as State.Loaded).data.isLoadingMore)
        assertEquals(1, newState.data.page)
    }

    @Test
    fun `verify that when LoadMoreSuccess action is called then Loaded is returned with new repositories`() {
        val initialState =
            RepositorySearchState(
                repositories = FakeDataGenerator.getRepositoryUiModel(),
                query = "react",
                page = 2,
            )

        val newState =
            reducer(
                State.Loaded(data = initialState),
                RepositorySearchAction.LoadMoreSuccess(repositories = FakeDataGenerator.getRepositoryUiModel()),
            )

        assertTrue(newState is State.Loaded)
        assertEquals(false, (newState as State.Loaded).data.isLoadingMore)
        assertEquals(2, newState.data.page)
        assertEquals(2, newState.data.repositories.size)
    }
}
