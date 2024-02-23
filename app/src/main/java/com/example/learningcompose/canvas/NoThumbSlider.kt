package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.animation.explosion.dpToPx
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun NoThumbSlider() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var volume by remember {
            mutableFloatStateOf(0f)
        }

        Slider(
            value = volume,
            onValueChange = {
                volume = it
            },
            modifier = Modifier
                .padding(16.dp)
                .drawWithContent {
                    drawContent()
                    // Custom draw modifier to hide the thumb indicator
                    drawCircle(color = Color.Transparent, radius = 0f)
                }
        )

        DraggableProgressIndicator()


    }
}

@Composable
fun DraggableProgressIndicator() {
    var progress by remember { mutableStateOf(0.5f) }

    BoxWithConstraints(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primary)
            .height(16.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    progress.plus(change.scrollDelta.x)
                }
            }
    ) {

        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        val context = LocalContext.current

        val maxWidthInPx = remember(maxWidth.value) {
            maxWidth.value.dpToPx()
        }

        val maxHeightInPx = remember(maxHeight.value) {
            maxHeight.value.dpToPx()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .height(100.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        progress = change.position.x / maxWidthInPx
                    }

                    detectTapGestures(
                        onTap = { offset ->
                            progress = offset.x / maxWidthInPx
                        }
                    )

                }
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawRect(
                color = Color.Red,
                topLeft = Offset(0f, canvasHeight * 0.25f),
                size = Size(
                    width = canvasWidth * progress.coerceAtMost(1f),
                    height = canvasHeight * 0.5f
                )
            )
        }
    }
}

@Preview
@Composable
fun NoThumbSliderPreview() {
    LearningComposeTheme {
        NoThumbSlider()
    }
}

@Preview
@Composable
fun NoThumbSliderPreview2() {
    LearningComposeTheme {
        DraggableProgressIndicator()
    }
}