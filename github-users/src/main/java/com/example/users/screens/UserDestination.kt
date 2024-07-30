package com.example.users.screens

import kotlinx.serialization.Serializable

@Serializable
sealed interface UserDestination {
    @Serializable
    data object UserSearch

    @Serializable
    data class UserDetails(val userJson: String)
}
