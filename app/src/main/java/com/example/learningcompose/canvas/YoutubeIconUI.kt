package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun YouTubeIconUI() {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .background(Color.White)
            .padding(12.dp)
    ) {
        val width = size.width
        val height = size.width

        drawRoundRect(
            color = Color.Red,
            topLeft = Offset(x = 0f, y = height * 0.125f),
            size = Size(width = width, height = height * 0.75f),
            cornerRadius = CornerRadius(width * .2f, height * .2f)
        )

        val path = Path().apply {
            moveTo(width * 0.4f, height * 0.35f)
            lineTo(width * 0.4f, height * 0.65f)
            lineTo(width * 0.7f, height * 0.5f)
            close()
        }

        drawPath(
            path = path,
            color = Color.White
        )


    }
}

@Preview
@Composable
fun PreviewYouTubeIconUI() {
    LearningComposeTheme {
        YouTubeIconUI()
    }
}