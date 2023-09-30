package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun GooglePhotosIconUI(

) {

    Canvas(
        modifier = Modifier.size(200.dp)
    ) {
        val width = size.width
        val height = size.width

        drawRect(color = Color.White)

        drawArc(
            color = Color.Blue,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(width / 2, height / 4),
            size = Size(width / 2, height / 2)
        )

        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = true,
            topLeft = Offset(0f, height / 4),
            size = Size(width / 2, height / 2)
        )

        drawArc(
            color = Color.Red,
            startAngle = 90f,
            sweepAngle = -180f,
            useCenter = true,
            topLeft = Offset(width / 4, 0f),
            size = Size(width / 2, height / 2)
        )

        drawArc(
            color = Color.Yellow,
            startAngle = 90f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(width / 4, height / 2f),
            size = Size(width / 2, height / 2)
        )

    }

}


@Preview
@Composable
fun PreviewGooglePhotosIconUI() {
    LearningComposeTheme {
        GooglePhotosIconUI()
    }
}