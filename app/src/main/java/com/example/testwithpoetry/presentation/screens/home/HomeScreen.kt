package com.example.testwithpoetry.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testwithpoetry.R
import com.example.testwithpoetry.presentation.components.BottomNavigationBar
import com.example.testwithpoetry.presentation.components.NavigationTab
import com.example.testwithpoetry.presentation.screens.authordetail.AuthorDetailScreen
import com.example.testwithpoetry.presentation.screens.authorslist.AuthorsListScreen
import com.example.testwithpoetry.presentation.screens.profile.ProfileScreen
import com.example.testwithpoetry.utils.EMPTY

const val HOME_DESTINATION = "home"
private const val AUTHOR_NAME = "authorName"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    user: String,
    onNavigateToWelcome: () -> Unit
) {
    val navController = rememberNavController()
    var topAppBarTitle by remember { mutableStateOf(EMPTY) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(topAppBarTitle) }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = navController.currentBackStackEntry?.destination?.route ?: EMPTY,
                onTabSelected = { navController.navigate(it) }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationTab.Poetry.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationTab.Poetry.route) {
                topAppBarTitle = stringResource(R.string.label_poetry_title, user)
                AuthorsListScreen(onNavigateToDetail = { author ->
                    navController.navigate("${NavigationTab.AuthorDetails.route}/${author.name}")
                })
            }

            composable(
                route = "${NavigationTab.AuthorDetails.route}/{$AUTHOR_NAME}",
                arguments = listOf(
                    navArgument(AUTHOR_NAME) { type = NavType.StringType }
                )) {
                val authorName = it.arguments?.getString(AUTHOR_NAME) ?: EMPTY
                topAppBarTitle = authorName
                AuthorDetailScreen(authorName = authorName)
            }

            composable(NavigationTab.Account.route) {
                topAppBarTitle = stringResource(R.string.label_profile_title)
                ProfileScreen(onNavigateToWelcome = onNavigateToWelcome)
            }
        }
    }
}

