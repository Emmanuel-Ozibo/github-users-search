package com.example.users.di.bindings

import com.example.users.remote.repository.UserRepository
import com.example.users.remote.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserRepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}
