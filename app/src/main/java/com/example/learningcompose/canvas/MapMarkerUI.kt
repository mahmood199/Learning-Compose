package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun MapMarkerUI(
    message: String,
    modifier: Modifier = Modifier
) {

    val height = 150f
    val widthPadding = 80.dp.value

    val textMeasurer = rememberTextMeasurer()

    val result = textMeasurer.measure(
        text = message,
        overflow = TextOverflow.Ellipsis
    )

    val width = maxOf(200f, result.size.width + widthPadding)
    val roundStart = height / 3

    val path = remember {
        Path().apply {
            arcTo(
                rect = Rect(
                    left = 0f,
                    top = 0f,
                    right = roundStart * 2,
                    bottom = roundStart * 2
                ),
                startAngleDegrees = -90f,
                sweepAngleDegrees = -180f,
                forceMoveTo = true
            )
            lineTo(width / 2 - roundStart / 2, height * 2 / 3)
            lineTo(width / 2, height)
            lineTo(width / 2 + roundStart / 2, height * 2 / 3)
            lineTo(width - roundStart, height * 2 / 3)
            arcTo(
                rect = Rect(
                    left = width - roundStart * 2,
                    top = 0f,
                    bottom = height * 2 / 3,
                    right = width
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = -180f,
                forceMoveTo = true
            )
            lineTo(roundStart, 0f)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)
        ) {

            drawPath(path, Color.White)
            drawPath(
                path = path,
                color = Color.Blue,
                style = Stroke(5f, cap = StrokeCap.Square)
            )
            drawText(
                textLayoutResult = result,
                topLeft = Offset(
                    x = (width - result.size.width) * .5f,
                    y = (height - result.size.height) * .25f
                )
            )

        }
    }

}


@Preview
@Composable
fun PreviewMapMarkerUI() {
    LearningComposeTheme {
        MapMarkerUI(
            message = "Mahmood Ahmad 8000 Mahmood Ahmad 8000",
            modifier = Modifier
                .background(Color.Green)
                .height(100.dp)
        )
    }
}