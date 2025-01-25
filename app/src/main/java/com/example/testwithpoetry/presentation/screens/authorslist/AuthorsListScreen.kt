package com.example.testwithpoetry.presentation.screens.authorslist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

const val POETRY_DESTINATION = "poetry"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorsListScreen(
    userName: String,
) {
    Scaffold { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {

        }
    }
}