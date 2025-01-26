package com.example.testwithpoetry.presentation.screens.authorslist

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testwithpoetry.R
import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.presentation.components.AuthorItem
import com.example.testwithpoetry.presentation.theme.SizeMd

const val POETRY_DESTINATION = "poetry"

@Composable
fun AuthorsListScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthorsListViewModel = hiltViewModel(),
    onNavigateToDetail: (Author) -> Unit
) {
    val authorsListState by viewModel.authorsListState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(AuthorsListEvent.GetAuthorsList)
    }

    Column(
        modifier = modifier
    ) {
        if (authorsListState.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (authorsListState.error.isNotBlank()) {
            Toast.makeText(context, authorsListState.error, Toast.LENGTH_SHORT).show()
        }

        authorsListState.authors?.let { authorsList ->
            if (authorsList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = SizeMd)
                ) {
                    items(authorsList) { author ->
                        AuthorItem(
                            modifier = Modifier.fillMaxWidth(),
                            author = author,
                            onAuthorClicked = {
                                onNavigateToDetail(author)
                            },
                            onFavoriteClicked = {
                                val event = if (author.isFavorite) {
                                    AuthorsListEvent.RemoveFavoriteAuthor(author)
                                } else {
                                    AuthorsListEvent.SaveFavoriteAuthor(author)
                                }
                                viewModel.onEvent(event)
                            }
                        )
                        HorizontalDivider()
                    }
                }
            } else {
                Text(stringResource(R.string.label_empty_authors))
            }
        }
    }
}