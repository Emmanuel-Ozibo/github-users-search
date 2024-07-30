package com.example.repository.mappers

import com.example.core.errors.MapperNotImplementedException
import com.example.core.utils.Mapper
import com.example.repository.models.Owner
import com.example.repository.remote.models.OwnerRemoteModel
import javax.inject.Inject

class OwnerRemoteModelToOwnerMapper
    @Inject
    constructor() : Mapper<OwnerRemoteModel, Owner> {
        override fun from(output: Owner): OwnerRemoteModel {
            throw MapperNotImplementedException()
        }

        override fun to(input: OwnerRemoteModel): Owner {
            return Owner(
                id = input.id,
                login = input.login ?: "",
                avatarUrl = input.avatarUrl ?: "",
            )
        }
    }
