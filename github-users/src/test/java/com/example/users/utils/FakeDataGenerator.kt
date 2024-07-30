package com.example.users.utils

import com.example.users.remote.models.UserRemoteModel
import com.example.users.remote.models.UserRemoteRepositoryModel
import com.example.users.screens.models.User
import com.example.users.screens.models.UserRepositoryModel
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Date

object FakeDataGenerator {
    fun getResourceAsText(path: String): String {
        val uri = ClassLoader.getSystemResource(path).toURI()
        return String(Files.readAllBytes(Paths.get(uri)))
    }

    fun getUiUsers(): List<User> =
        listOf(
            User(
                id = 323,
                login = "mojombo",
                avatar = "https://avatars.githubusercontent.com/u/1?v=4",
                name = "Tom Preston-Werner",
                publicRepos = 66,
                followers = 23940,
                following = 11,
                location = "San Francisco",
                blog = "http://tom.preston-werner.com",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                email = "emmanuel@gmail.com",
                bio = "Software Engineer",
                twitterUsername = "mojombo",
            ),
        )

    fun getRemoteUsers(): List<UserRemoteModel> =
        listOf(
            UserRemoteModel(
                id = 323,
                login = "mojombo",
                avatar = "https://avatars.githubusercontent.com/u/1?v=4",
                name = "Tom Preston-Werner",
                publicRepos = 66,
                followers = 23940,
                following = 11,
                location = "San Francisco",
                blog = "http://tom.preston-werner.com",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                email = "emmanuel@gmail.com",
                bio = "Software Engineer",
                twitterUsername = "mojombo",
            ),
        )

    fun getUiRepositoryModels(): List<UserRepositoryModel> =
        listOf(
            UserRepositoryModel(
                id = 1234,
                name = "mojombo",
                description = "description",
                language = "kotlin",
                stargazersCount = 123,
                forksCount = 123,
                openIssuesCount = 123,
                visibility = "public",
                updatedAt = Date().toFormattedDate(),
                fullName = "mojombo/mojombo",
            ),
        )

    fun getUserRemoteRepositories() =
        listOf(
            UserRemoteRepositoryModel(
                id = 1234,
                name = "mojombo",
                description = "description",
                language = "kotlin",
                stargazersCount = 123,
                forksCount = 123,
                openIssuesCount = 123,
                visibility = "public",
                fullName = "mojombo/mojombo",
                updateAt = Date(),
            ),
        )
}
