package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MessengerIconUI(

) {
    val colors = remember {
        listOf(Color(0xFF02b8f9), Color(0xFF0277fe))
    }
    Canvas(
        modifier = Modifier.size(400.dp)
    ) {
        val width = size.width
        val height = size.width

        drawOval(
            brush = Brush.verticalGradient(colors),
            topLeft = Offset(0f, height * 0.05f),
            size = Size(width, height * 0.90f)
        )

        val electricPath = Path().apply {
            moveTo(x = width * 0.2f, y = height * 0.6f)
            lineTo(x = width * 0.45f, y = height * 0.35f)
            lineTo(x = width * 0.56f, y = height * 0.5f)
            lineTo(x = width * 0.75f, y = height * 0.4f)
            lineTo(x = width * 0.55f, y = height * 0.65f)
            lineTo(x = width * 0.45f, y = height * 0.45f)
            close()
        }

        val trianglePath = Path().apply {
            moveTo(x = width * 0.15f, y = height)
            lineTo(x = width * 0.15f, y = 0.7f * height)
            lineTo(x = width * 0.35f, y = 0.9f * height)
            close()
        }


        drawPath(
            path = electricPath,
            color = Color.White
        )

        drawPath(
            path = trianglePath,
            brush = Brush.verticalGradient(colors),
        )

    }

}

@Preview
@Composable
fun PreviewMessengerIconUI() {
    MessengerIconUI()
}