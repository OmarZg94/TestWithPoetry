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

            })
        }
    }
}