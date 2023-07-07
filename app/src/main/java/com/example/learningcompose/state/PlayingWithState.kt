package com.example.learningcompose.state

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.LearningComposeTheme
import kotlin.random.Random

@Composable
fun ColouringTheBox(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {

        val color = remember {
            mutableStateOf(Color.Green)
        }

        ColorBox(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            color.value = it
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .background(color.value)
            .clickable {

            }
        )
    }

}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    newColor: (Color) -> Unit
) {
    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            newColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        }
    )
}


@Preview
@Composable
fun ColouringTheBoxPreview() {
    LearningComposeTheme {
        ColouringTheBox()
    }
}