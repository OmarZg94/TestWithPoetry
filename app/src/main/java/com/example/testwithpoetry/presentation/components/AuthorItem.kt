package com.example.testwithpoetry.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.testwithpoetry.R
import com.example.testwithpoetry.domain.models.Author

@Composable
fun AuthorItem(
    modifier: Modifier = Modifier,
    author: Author,
    onAuthorClicked: () -> Unit,
    onFavoriteClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    onAuthorClicked()
                },
            text = author.name
        )
        val icon = if (author.isFavorite) {
            painterResource(R.drawable.ic_star)
        } else {
            painterResource(R.drawable.ic_star_outline)
        }
        val contentDescription = if (author.isFavorite) {
            stringResource(R.string.content_description_favorite_author)
        } else {
            stringResource(R.string.content_description_not_favorite_author)
        }
        Icon(
            modifier = Modifier.clickable {
                onFavoriteClicked()
            },
            painter = icon,
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
fun AuthorItemPreview() {
    val authors = listOf(
        Author(name = "Author 1", isFavorite = false),
        Author(name = "Author 2", isFavorite = true)
    )
    LazyColumn {
        items(authors) {
            AuthorItem(author = it,
                onAuthorClicked = { },
                onFavoriteClicked = { })
        }
    }
}