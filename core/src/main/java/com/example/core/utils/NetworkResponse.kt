package com.example.core.utils

sealed class NetworkResponse<out T> {
    class Success<out T>(val response: T) : NetworkResponse<T>()

    data class Failure(val errorMessage: String) : NetworkResponse<Nothing>()

    /**
     * When successful use the transform, else keep it moving.
     */
    fun <R : Any> mapSuccess(transform: (T) -> R): NetworkResponse<R> {
        return when (this) {
            is Success -> Success(transform(response))
            is Failure -> this
        }
    }
}
