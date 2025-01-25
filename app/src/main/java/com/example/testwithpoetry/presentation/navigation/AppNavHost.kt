package com.example.testwithpoetry.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testwithpoetry.presentation.screens.home.HOME_DESTINATION
import com.example.testwithpoetry.presentation.screens.home.HomeScreen
import com.example.testwithpoetry.presentation.screens.welcome.WELCOME_DESTINATION
import com.example.testwithpoetry.presentation.screens.welcome.WelcomeScreen
import com.example.testwithpoetry.utils.EMPTY

const val USER_NAME = "userName"

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController, startDestination = startDestination) {
        composable(WELCOME_DESTINATION) {
            WelcomeScreen(onNavigateToMain = { userName ->
                val route = "$HOME_DESTINATION/$userName"
                navController.navigate(route) {
                    popUpTo(WELCOME_DESTINATION) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = "$HOME_DESTINATION/{$USER_NAME}",
            arguments = listOf(
                navArgument(USER_NAME) { type = NavType.StringType }
            )
        ) {
            val userName = it.arguments?.getString(USER_NAME) ?: EMPTY
            HomeScreen(user = userName)
        }
    }
}