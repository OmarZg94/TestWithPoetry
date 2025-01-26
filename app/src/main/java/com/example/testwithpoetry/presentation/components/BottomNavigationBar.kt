package com.example.testwithpoetry.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.testwithpoetry.R
import com.example.testwithpoetry.presentation.screens.authordetail.DETAIL_DESTINATION
import com.example.testwithpoetry.presentation.screens.authorslist.POETRY_DESTINATION
import com.example.testwithpoetry.presentation.screens.profile.ACCOUNT_DESTINATION

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf(
        NavigationTab.Poetry,
        NavigationTab.Account
    )
    var tabSelected by remember { mutableStateOf(currentRoute) }

    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = tabSelected == tab.route,
                onClick = {
                    tabSelected = tab.route
                    onTabSelected(tab.route)
                },
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
    data object AuthorDetails :
        NavigationTab(DETAIL_DESTINATION, R.string.tab_author_details, R.drawable.ic_book)

    data object Account :
        NavigationTab(ACCOUNT_DESTINATION, R.string.tab_account, R.drawable.ic_account)
}