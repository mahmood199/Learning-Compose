package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotateRad
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun StackOverflowIconUI() {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .background(Color.White)
    ) {
        val width = size.width
        val height = size.width

        drawRoundRect(
            color = Color.Gray,
            topLeft = Offset(width * 0.05f, height * 0.8f),
            size = Size(width * 0.9f, height * 0.125f)
        )

        drawRoundRect(
            color = Color.Gray,
            topLeft = Offset(width * 0.05f, height * 0.55f),
            size = Size(width * 0.125f, height * 0.3f)
        )

        drawRoundRect(
            color = Color.Gray,
            topLeft = Offset(width * 0.825f, height * 0.55f),
            size = Size(width * 0.125f, height * 0.3f)
        )

        rotateRad(0f) {
            drawRect(
                color = Color.Green,
                topLeft = Offset(width * 0.2f, height * 0.675f),
                size = Size(width * 0.6f, height * 0.1f)
            )
        }

        rotateRad(0.062f) {
            drawRect(
                color = Color.Green,
                topLeft = Offset(width * 0.205f, height * 0.545f),
                size = Size(width * 0.6f, height * 0.1f)
            )
        }

        rotateRad(0.155f) {
            drawRect(
                color = Color.Green,
                topLeft = Offset(width * 0.210f, height * 0.405f),
                size = Size(width * 0.6f, height * 0.1f)
            )
        }

        rotateRad(0.248f) {
            drawRect(
                color = Color.Green,
                topLeft = Offset(width * 0.2f, height * 0.265f),
                size = Size(width * 0.6f, height * 0.1f)
            )
        }

        rotateRad(0.36f) {
            drawRect(
                color = Color.Green,
                topLeft = Offset(width * 0.175f, height * 0.120f),
                size = Size(width * 0.6f, height * 0.1f)
            )
        }


    }
}


@Preview
@Composable
fun PreviewStackOverflowIconUI() {
    LearningComposeTheme {
        StackOverflowIconUI()
    }
}