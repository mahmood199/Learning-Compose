package com.example.learningcompose.ui.theme.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


data class ItemViewState(
    val text: String
)

@Composable
fun MyComposeList(
    modifier: Modifier = Modifier,
    itemViewStates: List<ItemViewState>
) {
    val list = remember {
        itemViewStates
    }

    LazyColumn(modifier = modifier) {
        items(list) {
            MySimpleListItem(itemViewState = it)
        }
    }
}

@Composable
fun MySimpleListItem(itemViewState: ItemViewState) {
    Text(text = itemViewState.text)
}