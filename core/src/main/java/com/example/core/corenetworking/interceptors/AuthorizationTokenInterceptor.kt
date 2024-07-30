package com.example.core.corenetworking.interceptors

import com.example.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationTokenInterceptor
    @Inject
    constructor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val modifiedRequest =
                originalRequest.newBuilder()
                    .header("Authorization", "Bearer ${BuildConfig.GITHUB_TOKEN}")
                    .build()

            return chain.proceed(modifiedRequest)
        }
    }
