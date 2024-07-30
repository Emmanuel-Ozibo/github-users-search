package com.example.sculptor.components.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.nav.NavBarDestination.Home
import com.example.sculptor.components.nav.NavBarDestination.RepositorySearch
import com.example.sculptor.components.nav.NavBarDestination.UsersSearch

@Composable
fun GSBottomNavigation(
    modifier: Modifier = Modifier,
    navItems: List<NavBarDestination> = listOf(),
    selectedItem: NavBarDestination,
    onNavItemClick: (NavBarDestination) -> Unit = {},
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = SculptorTheme.space.one,
        containerColor = SculptorTheme.colors.primaryLight,
    ) {
        navItems.forEach { item ->
            val selected = selectedItem == item
            val textStyle = if (selected) SculptorTheme.typography.tinySemiBold else SculptorTheme.typography.tiny
            val icon = if (selected) item.selectedIcon else item.icon

            NavigationBarItem(
                selected = selected,
                onClick = { onNavItemClick(item) },
                colors =
                    NavigationBarItemDefaults.colors(
                        indicatorColor = SculptorTheme.colors.primaryLight,
                    ),
                label = {
                    Text(
                        text = item.title,
                        style = textStyle,
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = item.title,
                    )
                },
            )
        }
    }
}

@Preview
@Composable
private fun GSBottomNavigationPreview() {
    val navItems =
        listOf(
            Home,
            RepositorySearch,
            UsersSearch,
        )

    SculptorTheme {
        GSBottomNavigation(
            selectedItem = navItems.first(),
            navItems = navItems,
        )
    }
}
