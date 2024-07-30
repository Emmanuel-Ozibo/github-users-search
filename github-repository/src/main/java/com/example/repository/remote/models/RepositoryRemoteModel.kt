package com.example.repository.remote.models

import com.google.gson.annotations.SerializedName

data class RepositoryRemoteModel(
    val id: Int,
    val name: String?,
    val owner: OwnerRemoteModel,
    val description: String?,
    val language: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("forks_count")
    val forksCount: Int?,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int?,
)
