package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun TicketPath2(
    modifier: Modifier = Modifier
) {

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        LocalDensity.current
        val path = remember {
            getTicketPathVertical(
                size = Size(maxWidth.value, maxHeight.value)
            )
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path, Color.Red)
            drawLine(
                color = Color.Blue,
                start = Offset(0f, maxHeight.value / 2),
                end = Offset(maxWidth.value, maxHeight.value / 2),
                strokeWidth = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f),4f)
            )
        }

    }

}

private fun getTicketPathVertical(size: Size): Path {
    val middleY = size.height.div(other = 2f)
    val circleRadiusInPx = 25f
    return Path().apply {

        lineTo(x = circleRadiusInPx, y = 0F)

        lineTo(x = size.width - circleRadiusInPx, y = 0F)


        arcTo(
            rect = Rect(
                Offset(
                    size.width - circleRadiusInPx * 2,
                    0f
                ),
                Offset(
                    size.width,
                    circleRadiusInPx * 2
                )
            ),
            270f,
            90f,
            true
        )

        lineTo(x = size.width, y = middleY - circleRadiusInPx)

        arcTo(
            rect = Rect(
                left = size.width - circleRadiusInPx,
                top = middleY - circleRadiusInPx,
                right = size.width + circleRadiusInPx,
                bottom = middleY + circleRadiusInPx
            ),
            startAngleDegrees = 270f,
            sweepAngleDegrees = -180F,
            forceMoveTo = false
        )

        lineTo(x = size.width, y = size.height - circleRadiusInPx)
        arcTo(
            rect = Rect(
                Offset(
                    size.width - circleRadiusInPx * 2,
                    size.height - circleRadiusInPx * 2
                ),
                Offset(
                    size.width,
                    size.height
                )
            ),
            0f,
            90f,
            false
        )

        lineTo(circleRadiusInPx, size.height)

        arcTo(
            rect = Rect(
                Offset(
                    0f,
                    size.height - circleRadiusInPx * 2
                ),
                Offset(
                    circleRadiusInPx * 2,
                    size.height
                )
            ),
            90f,
            90f,
            false
        )

        arcTo(
            rect = Rect(
                left = 0F - circleRadiusInPx,
                top = middleY - circleRadiusInPx,
                right = circleRadiusInPx,
                bottom = middleY + circleRadiusInPx
            ),
            startAngleDegrees = 90f,
            sweepAngleDegrees = -180F,
            forceMoveTo = false
        )

        lineTo(x = 0F, y = size.height)

        lineTo(x = 0F, y = circleRadiusInPx)

        arcTo(
            rect = Rect(
                Offset(
                    0f,
                    0f
                ),
                Offset(
                    circleRadiusInPx * 2,
                    circleRadiusInPx * 2
                )
            ),
            startAngleDegrees = 180f,
            sweepAngleDegrees = 90F,
            forceMoveTo = false
        )

    }
}

@Preview
@Composable
fun PreviewTicketPath2() {
    LearningComposeTheme {
        TicketPath2(
            modifier = Modifier
                .height(200.dp)
                .width(600.dp)
        )
    }
}