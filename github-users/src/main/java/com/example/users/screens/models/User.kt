package com.example.users.screens.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class User(
    val id: Int,
    val login: String,
    val name: String,
    val avatar: String,
    val company: String,
    val blog: String,
    val location: String,
    val email: String,
    val bio: String,
    val twitterUsername: String,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
) : Parcelable
