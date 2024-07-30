package com.example.repository.remote

import com.example.repository.remote.models.RepositorySearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositorySearchApiService {
    @GET("search/repositories")
    suspend fun searchRepository(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): Response<RepositorySearchResponse>
}
