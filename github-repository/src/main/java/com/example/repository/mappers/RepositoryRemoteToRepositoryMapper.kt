package com.example.repository.mappers

import com.example.core.errors.MapperNotImplementedException
import com.example.core.utils.Mapper
import com.example.repository.models.Repository
import com.example.repository.remote.models.RepositoryRemoteModel
import javax.inject.Inject

class RepositoryRemoteToRepositoryMapper
    @Inject
    constructor(private val ownerMapper: OwnerRemoteModelToOwnerMapper) :
    Mapper<RepositoryRemoteModel, Repository> {
        override fun from(output: Repository): RepositoryRemoteModel {
            throw MapperNotImplementedException()
        }

        override fun to(input: RepositoryRemoteModel): Repository {
            return Repository(
                id = input.id,
                name = input.name ?: "",
                fullName = input.fullName ?: "",
                owner = ownerMapper.to(input.owner),
                description = input.description ?: "",
                language = input.language ?: "",
                stargazersCount = input.stargazersCount ?: 0,
                forksCount = input.forksCount ?: 0,
                openIssuesCount = input.openIssuesCount ?: 0,
            )
        }
    }
