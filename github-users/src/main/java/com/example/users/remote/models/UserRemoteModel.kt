package com.example.users.remote.models

import com.google.gson.annotations.SerializedName

data class UserRemoteModel(
    val id: Int,
    val name: String?,
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("public_repos")
    val publicRepos: Int?,
    val followers: Int?,
    val following: Int?,
)
