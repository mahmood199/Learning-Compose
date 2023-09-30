package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun InstagramIconUI() {
    val instaColors = remember {
        listOf(Color.Yellow, Color.Red, Color.Magenta)
    }
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .padding(12.dp)
    ) {
        val width = size.width
        val height = size.width

        drawRoundRect(
            brush = Brush.linearGradient(colors = instaColors),
            cornerRadius = CornerRadius(width * 0.25f, width * 0.25f),
            style = Stroke(width = width * 0.065f, cap = StrokeCap.Round)
        )

        drawCircle(
            brush = Brush.linearGradient(colors = instaColors),
            radius = width * 0.25f,
            style = Stroke(width = width * 0.065f, cap = StrokeCap.Round)
        )

        drawCircle(
            brush = Brush.linearGradient(colors = instaColors),
            radius = width / 15,
            center = Offset(width * 0.8f, height * 0.2f),
        )

    }

}


@Preview
@Composable
fun PreviewInstagramIconUI() {
    LearningComposeTheme {
        InstagramIconUI()
    }
}