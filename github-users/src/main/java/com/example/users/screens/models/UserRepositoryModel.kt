package com.example.users.screens.models

data class UserRepositoryModel(
    val id: Int,
    val name: String,
    val description: String,
    val language: String,
    val stargazersCount: Int,
    val fullName: String,
    val forksCount: Int,
    val openIssuesCount: Int,
    val visibility: String,
    val updatedAt: String,
)
