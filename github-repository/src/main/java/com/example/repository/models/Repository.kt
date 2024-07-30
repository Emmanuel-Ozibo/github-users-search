package com.example.repository.models

data class Repository(
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: Owner,
    val description: String,
    val language: String,
    val stargazersCount: Int,
    val forksCount: Int,
    val openIssuesCount: Int,
)
