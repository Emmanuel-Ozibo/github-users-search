package com.example.core.corenetworking.models

import com.google.gson.annotations.SerializedName

data class ErrorBody(
    val message: String,
    @SerializedName("documentation_url")
    val documentationUrl: String,
    val status: String,
)

/**
 * {
 *     "message": "Bad credentials",
 *     "documentation_url": "https://docs.github.com/rest",
 *     "status": "401"
 * }
 */
