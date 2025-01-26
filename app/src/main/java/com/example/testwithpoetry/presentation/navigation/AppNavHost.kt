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
                navController.navigate("$HOME_DESTINATION/$userName") {
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
            HomeScreen(
                user = userName,
                onNavigateToWelcome = {
                    navController.navigate(WELCOME_DESTINATION) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}