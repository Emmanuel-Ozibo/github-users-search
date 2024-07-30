package com.example.users.screens.search.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.users.mappers.UserRemoteModelToUserMapper
import com.example.users.remote.repository.UserRepository
import com.example.users.utils.FakeDataGenerator
import com.example.users.utils.runSideEffects
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UserSearchSideEffectTest {
    private val userRepository = mockk<UserRepository>()
    private val mapper = UserRemoteModelToUserMapper()

    private val userSearchSideEffect =
        UserSearchSideEffect(
            repository = userRepository,
            mapper = mapper,
        )

    @Test
    fun `verify that when SearchUsers action is called with a query and it's successful LoadedUsers is action is dispatched`() =
        runTest {
            val calledActions: MutableList<UserSearchAction> = mutableListOf()
            val dispatch: Dispatch<UserSearchAction> =
                { action: UserSearchAction -> calledActions.add(action) }

            coEvery { userRepository.searchUsers(any()) } returns
                NetworkResponse.Success(FakeDataGenerator.getRemoteUsers())

            runSideEffects(
                dispatch = dispatch,
                event = UserSearchAction.SearchUsers("emmanuel"),
                state = State.Uninitialised(),
                sideEffects = listOf(userSearchSideEffect),
            )

            assertTrue(calledActions.contains(UserSearchAction.ShowLoading))
            assertTrue(
                calledActions.contains(
                    UserSearchAction.LoadedUsers(
                        query = "emmanuel",
                        users = FakeDataGenerator.getUiUsers(),
                    ),
                ),
            )

            val loaded = calledActions.find { it is UserSearchAction.LoadedUsers }
            assertEquals(1, (loaded as UserSearchAction.LoadedUsers).users.size)
            assertEquals(FakeDataGenerator.getUiUsers(), loaded.users)
        }

    @Test
    fun `verify that when SearchUsers action is called without a query EmptySearchQuery is dispatched`() =
        runTest {
            val calledActions: MutableList<UserSearchAction> = mutableListOf()
            val dispatch: Dispatch<UserSearchAction> =
                { action: UserSearchAction -> calledActions.add(action) }

            coEvery { userRepository.searchUsers(any()) } returns
                NetworkResponse.Success(listOf())

            runSideEffects(
                dispatch = dispatch,
                event = UserSearchAction.SearchUsers(""),
                state = State.Uninitialised(),
                sideEffects = listOf(userSearchSideEffect),
            )

            assertTrue(calledActions.contains(UserSearchAction.EmptySearchQuery))
        }

    @Test
    fun `verify that when SearchUsers action is called with a query but not successful SearchUsersError is action is dispatched`() =
        runTest {
            val calledActions: MutableList<UserSearchAction> = mutableListOf()
            val dispatch: Dispatch<UserSearchAction> =
                { action: UserSearchAction -> calledActions.add(action) }

            coEvery { userRepository.searchUsers(any()) } returns
                NetworkResponse.Failure(errorMessage = "Api Error")

            runSideEffects(
                dispatch = dispatch,
                event = UserSearchAction.SearchUsers("emmanuel"),
                state = State.Uninitialised(),
                sideEffects = listOf(userSearchSideEffect),
            )

            assertTrue(calledActions.contains(UserSearchAction.ShowLoading))
            assertTrue(calledActions.contains(UserSearchAction.SearchUsersError(error = "Api Error")))
        }
}
