package com.example.users.screens.search.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.users.mappers.UserRemoteModelToUserMapper
import com.example.users.remote.repository.UserRepository
import com.example.users.screens.search.states.UserSearchState
import com.example.users.utils.FakeDataGenerator
import com.example.users.utils.runSideEffects
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LoadMoreUsersSideEffectTest {
    private val userRepository = mockk<UserRepository>()
    private val mapper = UserRemoteModelToUserMapper()

    private val loadMoreUsersSideEffect =
        LoadMoreUsersSideEffect(
            repository = userRepository,
            mapper = mapper,
        )

    @Test
    fun `verify that when LoadMoreUsers is called and it's successful it dispatches a LoadMoreUsersSuccessful action`() =
        runTest {
            val calledActions: MutableList<UserSearchAction> = mutableListOf()
            val dispatch: Dispatch<UserSearchAction> =
                { action: UserSearchAction -> calledActions.add(action) }

            coEvery { userRepository.searchUsers(any()) } returns
                NetworkResponse.Success(FakeDataGenerator.getRemoteUsers())

            runSideEffects(
                dispatch = dispatch,
                event = UserSearchAction.LoadMoreUsers,
                state = State.Loaded(data = UserSearchState(query = "emmanuel")),
                sideEffects = listOf(loadMoreUsersSideEffect),
            )

            assertTrue(
                calledActions.contains(
                    UserSearchAction.LoadMoreUsersSuccessful(
                        query = "emmanuel",
                        users = FakeDataGenerator.getUiUsers(),
                    ),
                ),
            )
        }

    @Test
    fun `verify that when LoadMoreUsers is called and but fails it dispatches a LoadMoreUsersError action`() =
        runTest {
            val calledActions: MutableList<UserSearchAction> = mutableListOf()
            val dispatch: Dispatch<UserSearchAction> =
                { action: UserSearchAction -> calledActions.add(action) }

            coEvery { userRepository.searchUsers(any()) } returns
                NetworkResponse.Failure(errorMessage = "Api Error")

            runSideEffects(
                dispatch = dispatch,
                event = UserSearchAction.LoadMoreUsers,
                state = State.Loaded(data = UserSearchState(query = "emmanuel")),
                sideEffects = listOf(loadMoreUsersSideEffect),
            )

            assertTrue(calledActions.contains(UserSearchAction.LoadMoreUsersError(error = "Api Error")))
        }
}
