package com.example.learningcompose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme
import kotlin.random.Random

@Composable
fun OverlappingUI(
    overlappingFactor: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    val factor = 1 - overlappingFactor

    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, contraints ->
            val placeables = measurables.map { it.measure(contraints) }
            val widthExceptFirst = placeables.subList(1, placeables.size).sumOf { it.width }
            val first = placeables.getOrNull(0)?.width ?: 0
            val width = (widthExceptFirst * factor + first).toInt()
            val height = placeables.maxOf { it.height }

            layout(width, height) {
                var x = 0
                var zIndex = 0f
                for (placeable in placeables) {
                    placeable.placeRelative(x, 0, zIndex)
                    x += (placeable.width * factor).toInt()
                    zIndex += (zIndex).toInt()
                }
            }
        }
    )
}


@Preview
@Composable
fun OverlappingUIPreview() {
    val numbers = remember {
        val random = Random
        buildList {
            for (i in 0..15) {
                val alpha = 255L shl 24 // Fully opaque alpha
                val red = random.nextInt(256).toLong() shl 16
                val green = random.nextInt(256).toLong() shl 8
                val blue = random.nextInt(256).toLong()

                val pair = Pair(i, alpha or red or green or blue)
                add(pair)
            }
        }
    }

    LearningComposeTheme {
        Column(modifier = Modifier.fillMaxSize()) {

            val state = rememberLazyListState()
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(12.dp)
            ) {
                item {
                    OverlappingUI(overlappingFactor = 0.15f) {
                        for (number in numbers) {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxWidth(0.15f)
                                    .aspectRatio(1f)
                                    .background(Color(number.second), CircleShape)
                                    .border(2.dp, Color.Blue, CircleShape)
                            ) {

                            }
                        }
                    }
                }
            }

            LazyRow(
                state = state,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(numbers.size) {
                    var offset = 0.dp
                    numbers.forEachIndexed { index, number ->
                        Box(
                            modifier = Modifier
                                .fillParentMaxWidth(0.15f)
                                .aspectRatio(1f)
                                .offset(offset)
                                .background(Color(number.second), CircleShape)
                                .border(2.dp, Color.Blue, CircleShape)
                        ) {

                        }
                        offset -= 8.dp
                    }
                }
            }

        }
    }
}