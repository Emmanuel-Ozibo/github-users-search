package com.example.users.screens.search.mvi

import com.example.core.corestate.state.State
import com.example.users.screens.search.states.UserSearchState
import com.example.users.utils.FakeDataGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UserSearchReducerTest {
    private val reducer = UserSearchReducer.reducer

    @Test
    fun `verify that when ShowLoading action is called then Loading State is returned`() {
        val newState =
            reducer(
                State.Uninitialised(),
                UserSearchAction.ShowLoading,
            )

        assertTrue(newState is State.Loading)
    }

    @Test
    fun `verify that when EmptySearchQuery action is called then Uninitialised State is returned`() {
        val newState =
            reducer(
                State.Uninitialised(),
                UserSearchAction.EmptySearchQuery,
            )

        assertTrue(newState is State.Uninitialised)
    }

    @Test
    fun `verify that when SearchUsers action is called then Loading State is returned`() {
        val newState =
            reducer(
                State.Uninitialised(),
                UserSearchAction.SearchUsers(query = "any"),
            )

        assertTrue(newState is State.Loading)
    }

    @Test
    fun `verify that when SearchUsersError action is called then Failed state is returned`() {
        val newState =
            reducer(
                State.Loading(),
                UserSearchAction.SearchUsersError(error = "test error message"),
            )

        assertTrue(newState is State.Failed)
    }

    @Test
    fun `verify that when LoadMoreUsersError action is called then the returned state is the same`() {
        val initialState =
            UserSearchState(
                query = "test query",
            )

        val newState =
            reducer(
                State.Loaded(data = initialState),
                UserSearchAction.LoadMoreUsersError(error = "test error message"),
            )

        assertEquals(State.Loaded(initialState).data, (newState as State.Loaded).data)
    }

    @Test
    fun `verify that when LoadMoreUsers action is called then the state is updated to load more`() {
        val initialState = UserSearchState()

        val newState =
            reducer(
                State.Loaded(data = initialState),
                UserSearchAction.LoadMoreUsers,
            )

        assertTrue(newState is State.Loaded)
        assertEquals(true, (newState as State.Loaded).data.isLoadingMore)
        assertEquals(2, (newState as State.Loaded).data.page)
    }

    @Test
    fun `verify that when LoadedUsers action is called then Loaded state is returned`() {
        val newState =
            reducer(
                State.Loading(),
                UserSearchAction.LoadedUsers(
                    query = "test",
                    users = FakeDataGenerator.getUiUsers(),
                ),
            )

        assertTrue(newState is State.Loaded)
        assertEquals(1, (newState as State.Loaded).data.users.size)
    }
}
