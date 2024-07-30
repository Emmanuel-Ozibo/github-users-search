package com.example.users.screens.details.mvi

import com.example.core.corestate.Dispatch
import com.example.core.corestate.state.State
import com.example.core.utils.NetworkResponse
import com.example.users.mappers.UserRepositoryRemoteModelToUserRepositoryModelMapper
import com.example.users.remote.repository.UserRepository
import com.example.users.utils.FakeDataGenerator
import com.example.users.utils.runSideEffects
import com.example.users.utils.toFormattedDate
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.Date

class LoadUserRepositoriesSideEffectTest {
    private val mapper = UserRepositoryRemoteModelToUserRepositoryModelMapper()
    private val userRepository = mockk<UserRepository>()

    private val loadUserRepositoriesSideEffect =
        LoadUserRepositoriesSideEffect(
            repository = userRepository,
            mapper = mapper,
        )

    @Test
    fun `verify that when LoadUserRepositories action is called and successful LoadedUserRepository action is dispatched`() =
        runTest {
            val calledActions: MutableList<UserDetailsAction> = mutableListOf()
            val dispatch: Dispatch<UserDetailsAction> =
                { action: UserDetailsAction -> calledActions.add(action) }

            coEvery { userRepository.getUserRepositories(any()) } returns
                NetworkResponse.Success(FakeDataGenerator.getUserRemoteRepositories())

            runSideEffects(
                dispatch = dispatch,
                event = UserDetailsAction.LoadUserRepositories("emmanuel"),
                state = State.Uninitialised(),
                sideEffects = listOf(loadUserRepositoriesSideEffect),
            )

            assertTrue(calledActions.contains(UserDetailsAction.ShowLoading))
            assertTrue(calledActions.contains(UserDetailsAction.LoadedUserRepository(FakeDataGenerator.getUiRepositoryModels())))

            val loaded = calledActions.find { it is UserDetailsAction.LoadedUserRepository }
            assertEquals(1, (loaded as UserDetailsAction.LoadedUserRepository).repositories.size)
            assertEquals(Date().toFormattedDate(), loaded.repositories[0].updatedAt)
        }

    @Test
    fun `verify that when LoadUserRepositories action is called and fails LoadUserRepositoryError action is dispatched`() =
        runTest {
            val calledActions: MutableList<UserDetailsAction> = mutableListOf()
            val dispatch: Dispatch<UserDetailsAction> =
                { action: UserDetailsAction -> calledActions.add(action) }

            coEvery { userRepository.getUserRepositories(any()) } returns
                NetworkResponse.Failure(errorMessage = "Api Error")

            runSideEffects(
                dispatch = dispatch,
                event = UserDetailsAction.LoadUserRepositories("emmanuel"),
                state = State.Uninitialised(),
                sideEffects = listOf(loadUserRepositoriesSideEffect),
            )

            assertTrue(calledActions.contains(UserDetailsAction.ShowLoading))
            assertTrue(calledActions.contains(UserDetailsAction.LoadUserRepositoryError(error = "Api Error")))
        }
}
