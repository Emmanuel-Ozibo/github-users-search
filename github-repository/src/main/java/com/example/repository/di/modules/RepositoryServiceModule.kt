package com.example.repository.di.modules

import com.example.repository.remote.RepositorySearchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class RepositoryServiceModule {
    @Provides
    fun provideRepositorySearchApiService(retrofit: Retrofit): RepositorySearchApiService {
        return retrofit.create(RepositorySearchApiService::class.java)
    }
}
