package com.example.sculptor.components.nav

import androidx.annotation.DrawableRes
import com.example.sculptor.R
import kotlinx.serialization.Serializable

@Serializable
sealed class NavBarDestination(
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
) {
    @Serializable
    data object Home :
        NavBarDestination(
            title = "Home",
            icon = R.drawable.ic_home,
            selectedIcon = R.drawable.ic_home_selected,
        )

    @Serializable
    data object RepositorySearch :
        NavBarDestination(
            title = "Repository",
            icon = R.drawable.ic_search,
            selectedIcon = R.drawable.ic_search_selected,
        )

    @Serializable
    data object UsersSearch :
        NavBarDestination(
            title = "Users",
            icon = R.drawable.ic_user,
            selectedIcon = R.drawable.ic_user_selected,
        )
}
