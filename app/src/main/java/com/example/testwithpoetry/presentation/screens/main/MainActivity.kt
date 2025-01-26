package com.example.testwithpoetry.presentation.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.testwithpoetry.presentation.navigation.AppNavHost
import com.example.testwithpoetry.presentation.screens.home.HOME_DESTINATION
import com.example.testwithpoetry.presentation.screens.welcome.WELCOME_DESTINATION
import com.example.testwithpoetry.presentation.theme.TestWithPoetryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestWithPoetryTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel = hiltViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()

                // If user saved in db continue to authors
                LaunchedEffect(key1 = true) {
                    viewModel.onEvent(MainEvent.ValidateUser)
                }

                if (state.verificationCompleted) {
                    state.userVerified?.let {
                        AppNavHost(navController, "$HOME_DESTINATION/${it.name}")
                    } ?: AppNavHost(navController, WELCOME_DESTINATION)
                }
            }
        }
    }
}