package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun GoogleMicIconUI() {

    Canvas(
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .background(Color.White)
    ) {
        val width = size.width
        val height = size.width

        drawRect(
            color = Color.Green,
            size = Size(width = width * 0.1f, height / 5),
            topLeft = Offset(width * 0.45f, height * 0.7f)
        )

        drawArc(
            color = Color.Red,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = false,
            size = Size(width * 0.5f, height * 0.5f),
            topLeft = Offset(width * 0.25f, height * 0.2f),
            style = Stroke(width * 0.1f, cap = StrokeCap.Square)
        )

        drawArc(
            color = Color.Yellow,
            startAngle = 180f,
            sweepAngle = -45f,
            useCenter = false,
            size = Size(width * 0.5f, height * 0.5f),
            topLeft = Offset(width * 0.25f, height * 0.2f),
            style = Stroke(width * 0.105f, cap = StrokeCap.Square)
        )


        drawRoundRect(
            color = Color.Blue,
            size = Size(width = width / 5, height / 2),
            cornerRadius = CornerRadius(width / 10, width / 10),
            topLeft = Offset(width / 2 - width / 10, height / 10)
        )


    }
}

@Preview
@Composable
fun PreviewGoogleMicIconUI() {
    LearningComposeTheme {
        GoogleMicIconUI()
    }
}