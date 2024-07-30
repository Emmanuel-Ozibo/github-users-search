package com.example.repository.remote.models

import com.google.gson.annotations.SerializedName

data class OwnerRemoteModel(
    val id: Int,
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
)
