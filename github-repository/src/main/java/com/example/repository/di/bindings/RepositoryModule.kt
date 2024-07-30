package com.example.repository.di.bindings

import com.example.repository.remote.repository.SearchRepository
import com.example.repository.remote.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: SearchRepositoryImpl): SearchRepository
}
