package com.example.core.utils

import com.example.core.corenetworking.models.ErrorBody
import com.example.core.errors.network.CoreNetworkingException
import com.example.core.errors.network.NoInternetConnectionException
import com.google.gson.Gson
import kotlinx.coroutines.delay
import retrofit2.Response
import java.io.IOException

const val DEFAULT_RETRY_COUNT = 3
const val DEFAULT_RETRY_DELAY = 1000L

suspend fun <T, R> makeNetworkRequestWithRetryPolicy(
    block: suspend () -> Response<T>,
    transform: (T?) -> R,
    retryCount: Int = DEFAULT_RETRY_COUNT,
    retryDelay: Long = DEFAULT_RETRY_DELAY,
): NetworkResponse<R> {
    repeat(retryCount - 1) {
        val result =
            makeNetworkRequest(
                block = block,
                transform = transform,
            )

        if (result is NetworkResponse.Success) {
            return result
        }

        delay(retryDelay)
    }

    // last call
    return makeNetworkRequest(
        block = block,
        transform = transform,
    )
}

@Suppress("TooGenericExceptionCaught", "SwallowedException") // not so cool, optimise this later
suspend fun <T, R> makeNetworkRequest(
    block: suspend () -> Response<T>,
    transform: (T?) -> R,
): NetworkResponse<R> {
    return try {
        val networkResult = block()
        return if (!networkResult.isSuccessful) {
            val errorBodyString = networkResult.errorBody()?.string() ?: ""
            val errorBody =
                try {
                    Gson().fromJson(errorBodyString, ErrorBody::class.java)
                } catch (e: Exception) {
                    ErrorBody(
                        message = "Unknown error occurred",
                        status = "Failed",
                        documentationUrl = "In App",
                    )
                }
            NetworkResponse.Failure(errorMessage = errorBody.message)
        } else {
            NetworkResponse.Success(transform(networkResult.body()))
        }
    } catch (ex: NoInternetConnectionException) {
        NetworkResponse.Failure(errorMessage = ex.message ?: "No Internet Connection")
    } catch (ex: CoreNetworkingException) {
        NetworkResponse.Failure(errorMessage = ex.message ?: "An error occurred")
    } catch (ex: IOException) {
        NetworkResponse.Failure(errorMessage = ex.message ?: "An error occurred")
    }
}
