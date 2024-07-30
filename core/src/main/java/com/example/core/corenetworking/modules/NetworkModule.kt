package com.example.core.corenetworking.modules

import android.content.Context
import com.example.core.BuildConfig
import com.example.core.corenetworking.deserializer.DateDeserializer
import com.example.core.corenetworking.interceptors.AuthorizationTokenInterceptor
import com.example.core.corenetworking.interceptors.NoInternetConnectionInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideApplicationContext(
        @ApplicationContext context: Context,
    ): Context {
        return context
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            redactHeader("Authorization")
        }
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()
    }

    @Provides
    fun provideOkHttpClient(
        okHttpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationTokenInterceptor: AuthorizationTokenInterceptor,
        noInternetConnectionInterceptor: NoInternetConnectionInterceptor,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(noInternetConnectionInterceptor)
            .addNetworkInterceptor(okHttpLoggingInterceptor)
            .addNetworkInterceptor(authorizationTokenInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.GITHUB_API_BASE_URL)
            .build()
    }
}
