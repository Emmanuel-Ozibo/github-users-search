package com.example.users.mappers

import com.example.core.errors.MapperNotImplementedException
import com.example.core.utils.Mapper
import com.example.users.remote.models.UserRemoteRepositoryModel
import com.example.users.screens.models.UserRepositoryModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class UserRepositoryRemoteModelToUserRepositoryModelMapper
    @Inject
    constructor() :
    Mapper<UserRemoteRepositoryModel, UserRepositoryModel> {
        override fun from(output: UserRepositoryModel): UserRemoteRepositoryModel {
            throw MapperNotImplementedException()
        }

        override fun to(input: UserRemoteRepositoryModel): UserRepositoryModel {
            return UserRepositoryModel(
                id = input.id,
                name = input.name ?: "",
                description = input.description ?: "",
                language = input.language ?: "",
                stargazersCount = input.stargazersCount ?: 0,
                fullName = input.fullName ?: "",
                forksCount = input.forksCount ?: 0,
                openIssuesCount = input.openIssuesCount ?: 0,
                visibility = input.visibility ?: "",
                updatedAt = getDate(input.updateAt ?: Date()),
            )
        }

        private fun getDate(inputDate: Date): String {
            val formatter = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
            return formatter.format(inputDate)
        }
    }
