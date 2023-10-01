package com.example.learningcompose.canvas

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme
import kotlinx.collections.immutable.toPersistentList
import kotlin.random.Random

@Composable
fun MusicPlayerProgressUI() {

    var value by remember {
        mutableStateOf(false)
    }

    val progress by animateFloatAsState(
        targetValue = if (value) 1f else 0f,
        label = "Progress Value",
        animationSpec = tween(
            durationMillis = 3000,
            easing = LinearEasing
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .clickable {
                value = !value
            }
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp
        val screenHeight = LocalConfiguration.current.screenHeightDp

        val barsCount = remember {
            screenWidth / 3
        }

        val random = remember {
            Random.Default
        }

        val heights = remember {
            buildList {
                for (i in 0..barsCount) {
                    add(element = screenHeight * (random.nextFloat() * 0.5f + 0.2f))
                }
            }.toPersistentList()
        }

        var startX = remember {
            40f
        }
        val width = remember {
            screenWidth
        }
        val height = remember {
            screenHeight
        }

        val path = remember {
            Path().apply {
                heights.forEachIndexed { index, barHeight ->
                    if (startX < 900) {
                        addRoundRect(
                            RoundRect(
                                Rect(
                                    offset = Offset(
                                        x = startX,
                                        y = height * 0.5f - barHeight / 2
                                    ),
                                    size = Size(width = width * 0.01f, barHeight)
                                ),
                                10f,
                                10f
                            )
                        )
                    }
                    startX += 20f
                }
                close()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Canvas(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
            ) {

                val canvasWidth = size.width
                val canvasHeight = size.width

                drawRect(
                    color = Color.Red,
                    size = Size(size.width * progress, size.height)
                )

                clipPath(path = path, clipOp = ClipOp.Difference) {
                    drawRoundRect(
                        color = Color.Green,
                        size = Size(canvasWidth, canvasHeight),
                        topLeft = Offset(width * 0.0f, height * 0.0f),
                        cornerRadius = CornerRadius(width * 0.05f, width * 0.05f)
                    )
                }

            }
        }
    }

}


@Preview
@Composable
fun PreviewMusicPlayerProgressUI() {
    LearningComposeTheme {
        MusicPlayerProgressUI()
    }
}