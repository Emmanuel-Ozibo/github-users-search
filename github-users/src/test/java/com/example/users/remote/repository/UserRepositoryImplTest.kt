package com.example.users.remote.repository

import com.example.core.utils.NetworkResponse
import com.example.users.remote.UserApiService
import com.example.users.utils.FakeDataGenerator
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("MaxLineLength")
class UserRepositoryImplTest {
    private lateinit var apiService: UserApiService

    private lateinit var userRepository: UserRepository
    private lateinit var mockWebServer: MockWebServer

    private val getUserRepositoriesRegex = Regex("^/users/([^/]+)/repos$")
    private val getUserDetailsRegex = Regex("^/users/[^/]+$")

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit =
            Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit.create(UserApiService::class.java)
        userRepository = UserRepositoryImpl(apiService)

        mockWebServer.dispatcher =
            object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return when {
                        request.path?.contains("/search/users") == true -> {
                            MockResponse()
                                .setResponseCode(200)
                                .setBody(FakeDataGenerator.getResourceAsText(path = "user_search_success.json"))
                        }

                        getUserDetailsRegex.matches(request.path ?: "") -> {
                            MockResponse()
                                .setResponseCode(200)
                                .setBody(FakeDataGenerator.getResourceAsText(path = "get_user_detail_response.json"))
                        }

                        getUserRepositoriesRegex.matches(request.path ?: "") -> {
                            MockResponse()
                                .setResponseCode(200)
                                .setBody(FakeDataGenerator.getResourceAsText(path = "get_user_repositories_success.json"))
                        }

                        else -> {
                            MockResponse()
                                .setResponseCode(404)
                                .setBody(FakeDataGenerator.getResourceAsText("github_error.json"))
                        }
                    }
                }
            }
    }

    @Test
    fun `verify that when getUserRepositories is called with a username and it's successful NetworkResponseSuccess is returned`() =
        runTest {
            val username = "emmanuel"
            val response = userRepository.getUserRepositories(username = username)

            assertTrue(response is NetworkResponse.Success)
            assertEquals(1, (response as NetworkResponse.Success).response.size)
        }

    @Test
    fun `verify that when getUserRepositories is called with a username and is not successful NetworkResponseFailure is returned`() =
        runTest {
            mockWebServer.dispatcher =
                object : Dispatcher() {
                    override fun dispatch(request: RecordedRequest): MockResponse {
                        return MockResponse()
                            .setResponseCode(404)
                            .setBody(FakeDataGenerator.getResourceAsText(path = "github_error.json"))
                    }
                }

            val username = "emmanuel"
            val response = userRepository.getUserRepositories(username = username)

            assertTrue(response is NetworkResponse.Failure)
            assertEquals("Bad credentials", (response as NetworkResponse.Failure).errorMessage)
        }

    @Test
    fun `verify that when searchUsers is called with a query and it's successful NetworkResponseSuccess is returned`() =
        runTest {
            val query = "emmanuel"
            val response = userRepository.searchUsers(query = query, page = 2)

            assertTrue(response is NetworkResponse.Success)
            val userRemoteModels = (response as NetworkResponse.Success).response

            assertEquals(1, userRemoteModels.size)
            assertEquals("mojombo", userRemoteModels[0].login)
            assertEquals("https://avatars.githubusercontent.com/u/1?v=4", userRemoteModels[0].avatar)
            assertEquals("Tom Preston-Werner", userRemoteModels[0].name)
            assertEquals(66, userRemoteModels[0].publicRepos)
            assertEquals(23940, userRemoteModels[0].followers)
            assertEquals(11, userRemoteModels[0].following)
            assertEquals("San Francisco", userRemoteModels[0].location)
            assertEquals("http://tom.preston-werner.com", userRemoteModels[0].blog)
        }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
