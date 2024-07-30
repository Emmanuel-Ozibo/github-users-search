package com.example.repository.utils

import com.example.repository.models.Owner
import com.example.repository.models.Repository
import com.example.repository.remote.models.OwnerRemoteModel
import com.example.repository.remote.models.RepositoryRemoteModel
import java.nio.file.Files
import java.nio.file.Paths

object FakeDataGenerator {
    fun getResourceAsText(path: String): String {
        val uri = ClassLoader.getSystemResource(path).toURI()
        return String(Files.readAllBytes(Paths.get(uri)))
    }

    fun getRemoteRepositories(): List<RepositoryRemoteModel> {
        return listOf(
            RepositoryRemoteModel(
                id = 1,
                name = "test",
                description = "test description",
                fullName = "react/Typescript",
                forksCount = 20,
                stargazersCount = 30,
                language = "TypeScript",
                openIssuesCount = 30,
                owner =
                    OwnerRemoteModel(
                        id = 1,
                        login = "test",
                        avatarUrl = "avatar.com/400px",
                    ),
            ),
        )
    }

    fun getRepositoryUiModel(): List<Repository> {
        return listOf(
            Repository(
                id = 1,
                name = "test",
                description = "test description",
                fullName = "react/Typescript",
                forksCount = 20,
                stargazersCount = 30,
                language = "TypeScript",
                openIssuesCount = 30,
                owner =
                    Owner(
                        id = 1,
                        login = "test",
                        avatarUrl = "avatar.com/400px",
                    ),
            ),
        )
    }
}
