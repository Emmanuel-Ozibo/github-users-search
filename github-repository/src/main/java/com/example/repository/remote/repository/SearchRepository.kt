package com.example.repository.remote.repository

import com.example.core.utils.NetworkResponse
import com.example.core.utils.makeNetworkRequestWithRetryPolicy
import com.example.repository.remote.RepositorySearchApiService
import com.example.repository.remote.models.RepositoryRemoteModel
import javax.inject.Inject

interface SearchRepository {
    suspend fun search(
        query: String,
        page: Int = 1,
        perPage: Int = 10,
    ): NetworkResponse<List<RepositoryRemoteModel>>
}

class SearchRepositoryImpl
    @Inject
    constructor(private val apiService: RepositorySearchApiService) :
    SearchRepository {
        override suspend fun search(
            query: String,
            page: Int,
            perPage: Int,
        ): NetworkResponse<List<RepositoryRemoteModel>> {
            return makeNetworkRequestWithRetryPolicy(
                block = {
                    apiService.searchRepository(query = query, perPage = perPage, page = page)
                },
                transform = { result -> result?.items ?: emptyList() },
            )
        }
    }
