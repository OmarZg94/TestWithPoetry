package com.example.testwithpoetry.presentation.screens.authordetail

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
import com.example.testwithpoetry.presentation.components.PoemDialog
import com.example.testwithpoetry.presentation.components.TitleItem
import com.example.testwithpoetry.presentation.theme.SizeMd
import com.example.testwithpoetry.utils.EMPTY

const val DETAIL_DESTINATION = "detail"

@Composable
fun AuthorDetailScreen(
    modifier: Modifier = Modifier,
    authorName: String = EMPTY,
    viewModel: AuthorDetailViewModel = hiltViewModel()
) {
    val authorDetailState by viewModel.authorDetailState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(AuthorDetailEvent.GetTitles(authorName = authorName))
    }

    Column(
        modifier = modifier
    ) {
        if (authorDetailState.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        val toast = Toast.makeText(context, authorDetailState.message, Toast.LENGTH_SHORT)
        if (authorDetailState.message.isNotBlank()) {
            toast.show()
        }

        authorDetailState.titles?.let { titlesList ->
            if (titlesList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = SizeMd)
                ) {
                    items(titlesList) { poemTitle ->
                        TitleItem(
                            modifier = Modifier.fillMaxWidth(),
                            poemTitle = poemTitle,
                            onTitleClicked = { title ->
                                viewModel.onEvent(
                                    AuthorDetailEvent.GetPoem(
                                        authorName = authorName,
                                        title = title
                                    )
                                )
                            }
                        )
                        HorizontalDivider()
                    }
                }
            } else {
                Text(stringResource(R.string.label_empty_titles))
            }
        }

        authorDetailState.poem?.let {
            val poem = it.first()
            if (authorDetailState.showPoemDialog) {
                PoemDialog(
                    title = poem.title,
                    poem = poem.lines
                ) {
                    viewModel.onEvent(AuthorDetailEvent.ClosePoemDialog)
                }
            }
        }
    }
}