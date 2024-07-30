package com.example.users.mappers

import com.example.core.errors.MapperNotImplementedException
import com.example.core.utils.Mapper
import com.example.users.remote.models.UserRemoteModel
import com.example.users.screens.models.User
import javax.inject.Inject

class UserRemoteModelToUserMapper
    @Inject
    constructor() : Mapper<UserRemoteModel, User> {
        override fun from(output: User): UserRemoteModel {
            throw MapperNotImplementedException()
        }

        override fun to(input: UserRemoteModel): User {
            return User(
                id = input.id,
                login = input.login,
                name = input.name ?: "",
                avatar = input.avatar ?: "",
                company = input.company ?: "",
                blog = input.blog ?: "",
                location = input.location ?: "",
                email = input.email ?: "",
                bio = input.bio ?: "",
                twitterUsername = input.twitterUsername ?: "",
                publicRepos = input.publicRepos ?: 0,
                followers = input.followers ?: 0,
                following = input.following ?: 0,
            )
        }
    }
