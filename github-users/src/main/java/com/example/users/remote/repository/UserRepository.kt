package com.example.users.remote.repository

import com.example.core.utils.NetworkResponse
import com.example.core.utils.makeNetworkRequestWithRetryPolicy
import com.example.users.remote.UserApiService
import com.example.users.remote.models.UserRemoteModel
import com.example.users.remote.models.UserRemoteRepositoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import okio.IOException
import javax.inject.Inject

interface UserRepository {
    suspend fun searchUsers(
        query: String,
        page: Int = 1,
        perPage: Int = 10,
    ): NetworkResponse<List<UserRemoteModel>>

    suspend fun getUserRepositories(username: String): NetworkResponse<List<UserRemoteRepositoryModel>>
}

class UserRepositoryImpl
    @Inject
    constructor(private val apiService: UserApiService) : UserRepository {
        override suspend fun searchUsers(
            query: String,
            page: Int,
            perPage: Int,
        ): NetworkResponse<List<UserRemoteModel>> =
            coroutineScope {
                try {
                    val response = apiService.searchUsers(query = query, page = page, perPage = perPage)

                    if (response.isSuccessful) {
                        val userNames = response.body()?.items?.map { it.login } ?: emptyList()

                        val deferredList =
                            userNames.map { username ->
                                async(Dispatchers.IO) {
                                    apiService.getUserDetails(username = username).body()
                                }
                            }

                        val list = deferredList.awaitAll()
                        NetworkResponse.Success(list.filterNotNull())
                    } else {
                        NetworkResponse.Failure(response.message())
                    }
                } catch (ex: IOException) {
                    NetworkResponse.Failure(ex.message.toString())
                }
            }

        override suspend fun getUserRepositories(username: String): NetworkResponse<List<UserRemoteRepositoryModel>> {
            return makeNetworkRequestWithRetryPolicy(
                block = { apiService.getUserRepositories(username = username) },
                transform = { result -> result ?: emptyList() },
            )
        }
    }
