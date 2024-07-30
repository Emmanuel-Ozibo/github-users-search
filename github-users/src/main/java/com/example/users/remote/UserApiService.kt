package com.example.users.remote

import com.example.users.remote.models.UserRemoteModel
import com.example.users.remote.models.UserRemoteRepositoryModel
import com.example.users.remote.models.UserSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
    ): Response<UserSearchResponse>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String,
    ): Response<UserRemoteModel>

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(
        @Path("username") username: String,
    ): Response<List<UserRemoteRepositoryModel>>
}
