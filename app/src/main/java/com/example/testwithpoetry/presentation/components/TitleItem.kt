package com.example.testwithpoetry.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testwithpoetry.domain.models.PoemTitle
import com.example.testwithpoetry.presentation.theme.SizeSm
import com.example.testwithpoetry.presentation.theme.SizeXL

@Composable
fun TitleItem(
    modifier: Modifier = Modifier,
    poemTitle: PoemTitle,
    onTitleClicked: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = SizeXL)
            .padding(vertical = SizeSm)
            .clickable {
                onTitleClicked(poemTitle.title)
            },
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            text = poemTitle.title
        )
    }
}

@Preview
@Composable
fun TitleItemPreview() {
    val authors = listOf(
        PoemTitle(title = "Title 1"),
        PoemTitle(title = "Title 2")
    )
    LazyColumn {
        items(authors) {
            TitleItem(poemTitle = it,
                onTitleClicked = { }
            )
        }
    }
}