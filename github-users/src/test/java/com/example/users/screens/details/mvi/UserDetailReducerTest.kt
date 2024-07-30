package com.example.users.screens.details.mvi

import com.example.core.corestate.state.State
import com.example.users.utils.FakeDataGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UserDetailReducerTest {
    val reducer = UserDetailReducer.reducer

    @Test
    fun `verify that when ShowLoading action is called then Loading State is returned`() {
        val newState =
            reducer(
                State.Uninitialised(),
                UserDetailsAction.ShowLoading,
            )

        assertTrue(newState is State.Loading)
    }

    @Test
    fun `verify that when LoadUserRepositories action is called then Loading State is returned`() {
        val newState =
            reducer(
                State.Loading(),
                UserDetailsAction.LoadUserRepositories(userName = "emmanuel"),
            )

        assertTrue(newState is State.Loading)
    }

    @Test
    fun `verify that when LoadUserRepositoryError action is called then Failed state is returned`() {
        val newState =
            reducer(
                State.Loading(),
                UserDetailsAction.LoadUserRepositoryError(error = "test error message"),
            )

        assertTrue(newState is State.Failed)
        assertEquals("test error message", (newState as State.Failed).errorMessage)
    }

    @Test
    fun `verify that when LoadedUserRepository action is called then Loaded state is returned`() {
        val newState =
            reducer(
                State.Loading(),
                UserDetailsAction.LoadedUserRepository(repositories = FakeDataGenerator.getUiRepositoryModels()),
            )

        assertTrue(newState is State.Loaded)
        assertEquals(1, (newState as State.Loaded).data.repositories.size)
    }
}
