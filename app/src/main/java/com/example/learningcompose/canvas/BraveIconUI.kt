package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun BraveIconUI() {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .size(200.dp)
            .clip(CircleShape)
            .background(Color.White)

    ) {
        val width = size.width
        val height = size.width

        val firstPath = Path().apply {
            moveTo(width * 0.30f, height * 0.10f)
            lineTo(width * 0.70f, height * 0.10f)
            lineTo(width * 0.75f, height * 0.20f)
            lineTo(width * 0.90f, height * 0.30f)
            close()
        }

        val secondPath = Path().apply {
            moveTo(width * 0.70f, height * 0.10f)
            lineTo(width * 0.30f, height * 0.10f)
            lineTo(width * 0.25f, height * 0.20f)
            lineTo(width * 0.10f, height * 0.30f)
            close()
        }

        val thirdPath = Path().apply {
            moveTo(width * 0.90f, height * 0.30f)
            lineTo(width * 0.80f, height * 0.35f)
            lineTo(width * 0.85f, height * 0.40f)
            close()
        }


        drawPath(
            path = firstPath,
            color = Color.Red
        )

        drawPath(
            path = secondPath,
            color = Color.Red
        )

        drawPath(
            path = thirdPath,
            color = Color.Blue
        )
    }
}

@Preview
@Composable
fun PreviewBraveIconUI() {
    LearningComposeTheme {
        BraveIconUI()
    }
}