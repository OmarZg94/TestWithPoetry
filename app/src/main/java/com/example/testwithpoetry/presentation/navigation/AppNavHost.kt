package com.example.testwithpoetry.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testwithpoetry.presentation.screens.main.MAIN_DESTINATION
import com.example.testwithpoetry.presentation.screens.main.MainScreen
import com.example.testwithpoetry.presentation.screens.welcome.WELCOME_DESTINATION
import com.example.testwithpoetry.presentation.screens.welcome.WelcomeScreen
import com.example.testwithpoetry.utils.EMPTY

const val USER_NAME = "userName"

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = WELCOME_DESTINATION) {
        composable(WELCOME_DESTINATION) {
            WelcomeScreen(onNavigateToMain = { userName ->
                navController.navigate("$MAIN_DESTINATION/$userName")
            })
        }
        composable(
            route = "$MAIN_DESTINATION/{$USER_NAME}",
            arguments = listOf(
                navArgument(USER_NAME) { type = NavType.StringType }
            )
        ) {
            val userName = it.arguments?.getString(USER_NAME) ?: EMPTY
            MainScreen(user = userName)
        }
    }
}