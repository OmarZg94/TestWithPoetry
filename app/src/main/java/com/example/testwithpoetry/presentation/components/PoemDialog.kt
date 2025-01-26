package com.example.testwithpoetry.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.testwithpoetry.R
import com.example.testwithpoetry.presentation.theme.SizeMd
import com.example.testwithpoetry.presentation.theme.SizeSm

@Composable
fun PoemDialog(
    title: String,
    poem: List<String>,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7F)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SizeMd)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(SizeSm))
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    items(poem) { line ->
                        Text(text = line)
                    }
                }
                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = onDismiss
                ) {
                    Text(stringResource(R.string.action_close))
                }
            }
        }
    }
}

@Preview
@Composable
fun PoemDialogPreview() {
    val poem = listOf("Line 1", "Line 2", "Line 3", "Line 4", "Line 5", "Line 6", "Line 7")
    PoemDialog(
        title = "Title",
        poem = poem

    ) { }
}