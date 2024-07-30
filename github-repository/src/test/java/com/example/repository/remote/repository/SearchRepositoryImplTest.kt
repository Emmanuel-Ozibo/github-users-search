package com.example.repository.remote.repository

import com.example.core.utils.NetworkResponse
import com.example.repository.remote.RepositorySearchApiService
import com.example.repository.utils.FakeDataGenerator
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertTrue

class SearchRepositoryImplTest {
    private lateinit var apiService: RepositorySearchApiService

    private lateinit var searchRepository: SearchRepository
    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit =
            Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit.create(RepositorySearchApiService::class.java)
        searchRepository = SearchRepositoryImpl(apiService)
    }

    @Test
    fun `ensure that when searchRepository search is successful NetworkResponseSuccess is returned`() =
        runTest {
            val mockResponse =
                MockResponse()
                    .setResponseCode(200)
                    .setBody(FakeDataGenerator.getResourceAsText(path = "repository_search_success.json"))

            mockWebServer.enqueue(mockResponse)
            val testQuery = "query"

            val response = searchRepository.search(query = testQuery)

            assertTrue(response is NetworkResponse.Success)
            assertEquals(1, response.response.size)
        }

    @Test
    fun `ensure that when searchRepository search fails NetworkResponseFailure is returned`() =
        runTest {
            val mockResponse =
                MockResponse()
                    .setResponseCode(401)
                    .setBody(FakeDataGenerator.getResourceAsText(path = "github_error.json"))

            mockWebServer.enqueue(mockResponse)
            val testQuery = "query"

            val response = searchRepository.search(query = testQuery)

            assertTrue(response is NetworkResponse.Failure)
            assertEquals("Bad credentials", response.errorMessage)
        }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
