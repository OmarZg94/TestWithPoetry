package com.example.testwithpoetry.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testwithpoetry.presentation.screens.welcome.WELCOME_DESTINATION
import com.example.testwithpoetry.presentation.screens.welcome.WelcomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = WELCOME_DESTINATION) {
        composable(WELCOME_DESTINATION) {
            WelcomeScreen(onNavigateToAuthors = { user ->
                navController.navigate("authorsList?userName=${user.name}")
            })
        }
        /*composable("authorsList?userName={userName}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            AuthorsListScreen(
                userName = userName,
                authors = authors, // Load from ViewModel
                onAuthorSelected = { author -> navController.navigate("authorDetails/${author.name}") },
                onFavoriteToggled = { author -> /* Update favorite status */ }
            )
        }
        composable("authorDetails/{authorName}") { backStackEntry ->
            val authorName = backStackEntry.arguments?.getString("authorName") ?: ""
            AuthorDetailsScreen(
                authorName = authorName,
                poems = poems, // Load from ViewModel
                onPoemSelected = { poemTitle -> showDialogWithPoem(poemTitle) }
            )
        }
        composable("profile") {
            ProfileScreen(user = user) // Pass the user data
        }*/
    }
}