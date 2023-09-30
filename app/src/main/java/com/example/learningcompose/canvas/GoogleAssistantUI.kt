package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun GoogleAssistantUI() {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .background(Color.White)
    ) {
        val width = size.width
        val height = size.width

        drawCircle(
            color = Color.Blue,
            radius = width * 0.175f,
            center = Offset(x = width * 0.3f, y = height * 0.4f)
        )

        drawCircle(
            color = Color.Red,
            radius = width * 0.1f,
            center = Offset(x = width * 0.6f, y = height * 0.5f)
        )

        drawCircle(
            color = Color.Yellow,
            radius = width * 0.12f,
            center = Offset(x = width * 0.6f, y = height * 0.775f)
        )

        drawCircle(
            color = Color.Green,
            radius = width * 0.075f,
            center = Offset(x = width * 0.8f, y = height * 0.35f)
        )

    }
}

@Preview
@Composable
fun PreviewGoogleAssistantUI() {
    LearningComposeTheme {
        GoogleAssistantUI()
    }
}