package com.example.learningcompose.ui.theme.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


sealed interface ItemViewState {
    data class ItemTypeOne(val text: String) : ItemViewState
    data class ItemTypeTwo(
        val text: String,
        val description: String,
    ) : ItemViewState
}

@Composable
fun MyComposeList(
    modifier: Modifier = Modifier,
    itemViewStates: List<ItemViewState>,
) {
    val list = remember {
        itemViewStates
    }

    LazyColumn(modifier = modifier) {
        items(list) { data ->
            when (data) {
                is ItemViewState.ItemTypeOne -> ItemOne(data)
                is ItemViewState.ItemTypeTwo -> ItemTwo(data)
            }
        }
    }
}

@Composable
fun ItemOne(data: ItemViewState.ItemTypeOne) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .clickable {

            },
        text = data.text
    )
}

@Composable
fun ItemTwo(data: ItemViewState.ItemTypeTwo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable {

            },
    ) {
        Text(text = data.text)
        Text(text = data.description)
    }
}

@Preview(showSystemUi = false)
@Composable
fun ShowListItems() {
    val data = remember {
        getList()
    }
    MyComposeList(itemViewStates = data)
}

fun getList() = buildList {
    add(ItemViewState.ItemTypeOne("First Item title"))
    add(ItemViewState.ItemTypeOne("Second Item title"))
    add(ItemViewState.ItemTypeTwo("Third item title", "Third Item description"))
    add(ItemViewState.ItemTypeTwo("Fourth item title", "Fourth Item description"))
    add(ItemViewState.ItemTypeOne("Fifth Item title"))
    add(ItemViewState.ItemTypeOne("Sixth Item title"))
    add(ItemViewState.ItemTypeTwo("Seventh item title", "Seventh Item description"))
    add(ItemViewState.ItemTypeTwo("Eight item title", "Eight Item description"))

}.toMutableList()
