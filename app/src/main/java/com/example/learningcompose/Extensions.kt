package com.example.learningcompose

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToTheLast() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1