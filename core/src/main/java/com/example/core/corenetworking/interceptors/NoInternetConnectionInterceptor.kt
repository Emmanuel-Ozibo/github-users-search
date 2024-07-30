package com.example.core.corenetworking.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.core.errors.network.NoInternetConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NoInternetConnectionInterceptor
    @Inject
    constructor(private val appContext: Context) :
    Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val hasInternet = hasInternetConnection()
            Log.d(NoInternetConnectionException::class.simpleName, "$hasInternet")

            if (hasInternet) {
                return chain.proceed(chain.request())
            }

            throw NoInternetConnectionException()
        }

        private fun hasInternetConnection(): Boolean {
            val connectivityManager =
                appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager?

            return if (connectivityManager != null) {
                val network = connectivityManager.activeNetwork ?: return false

                val capabilities = connectivityManager.getNetworkCapabilities(network)
                val hasInternet =
                    capabilities != null &&
                        (
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)
                        )
                hasInternet
            } else {
                false
            }
        }
    }
