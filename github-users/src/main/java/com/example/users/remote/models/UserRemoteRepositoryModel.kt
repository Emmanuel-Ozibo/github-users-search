package com.example.users.remote.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserRemoteRepositoryModel(
    val id: Int,
    val name: String?,
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
    val visibility: String?,
    @SerializedName("updated_at")
    val updateAt: Date?,
)
