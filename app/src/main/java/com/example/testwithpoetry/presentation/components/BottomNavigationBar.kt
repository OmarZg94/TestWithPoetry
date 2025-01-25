package com.example.testwithpoetry.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.testwithpoetry.R
import com.example.testwithpoetry.presentation.screens.authorslist.POETRY_DESTINATION

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf(
        NavigationTab.Poetry,
        NavigationTab.Account
    )

    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = currentRoute == tab.route,
                onClick = { onTabSelected(tab.route) },
                icon = {
                    Image(
                        painter = painterResource(tab.icon),
                        contentDescription = stringResource(tab.label)
                    )
                },
                label = { Text(stringResource(tab.label)) }
            )
        }
    }
}

sealed class NavigationTab(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    data object Poetry : NavigationTab(POETRY_DESTINATION, R.string.tab_poetry, R.drawable.ic_book)
    data object Account : NavigationTab("account", R.string.tab_account, R.drawable.ic_account)
}