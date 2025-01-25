package com.example.testwithpoetry.presentation.screens.authorslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.testwithpoetry.R
import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.presentation.theme.SizeMd

@ExperimentalMaterial3Api
@Composable
fun AuthorsListScreen(
    userName: String,
    authors: List<Author>,
    onAuthorSelected: (Author) -> Unit,
    onFavoriteToggled: (Author) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Welcome, $userName") })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Poetry Tab */ },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_book),
                            contentDescription = "Poetry"
                        )
                    },
                    label = { Text("Poetry") })
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Account Tab */ },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_account),
                            contentDescription = "Account"
                        )
                    },
                    label = { Text("Account") })
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(authors) { author ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onAuthorSelected(author) }
                        .padding(SizeMd),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(author.name, style = MaterialTheme.typography.bodyMedium)
                    IconButton(onClick = { onFavoriteToggled(author) }) {
                        Icon(
                            imageVector = if (author.isFavorite) Icons.Default.Star else Icons.Outlined.Star,
                            contentDescription = "Toggle Favorite"
                        )
                    }
                }
            }
        }
    }
}