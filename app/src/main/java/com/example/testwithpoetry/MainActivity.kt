package com.example.testwithpoetry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.testwithpoetry.presentation.navigation.AppNavHost
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
                AppNavHost(navController)
            }
        }
    }
}