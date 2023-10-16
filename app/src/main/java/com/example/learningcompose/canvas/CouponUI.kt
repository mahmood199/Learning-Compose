package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun CouponUI(
    modifier: Modifier = Modifier
) {

    var complete by remember {
        mutableStateOf(false)
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                complete = complete.not()
            }
            .zIndex(20f)
    ) {
        val width = size.width
        val height = size.height

        val minDimension = minOf(width, height)
        val halfRadius = minDimension * .125f * 0.5f

        val path = Path().apply {
            arcTo(
                rect = Rect(
                    left = width * .5f - halfRadius,
                    top = -halfRadius,
                    right = width * .5f + halfRadius,
                    bottom = +halfRadius,
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = -180f,
                forceMoveTo = true
            )

            arcTo(
                rect = Rect(
                    left = width * .5f - halfRadius,
                    top = height - halfRadius,
                    right = width * .5f + halfRadius,
                    bottom = height + halfRadius,
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = true
            )

            arcTo(
                rect = Rect(
                    top = height * .5f - halfRadius,
                    left = -halfRadius,
                    bottom = height * .5f + halfRadius,
                    right = +halfRadius,
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = -180f,
                forceMoveTo = true
            )

            arcTo(
                rect = Rect(
                    top = height * .5f - halfRadius,
                    left = width - halfRadius,
                    bottom = height * .5f + halfRadius,
                    right = width + halfRadius,
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 180f,
                forceMoveTo = true
            )
        }

        clipPath(path = path, clipOp = ClipOp.Difference) {
            drawRoundRect(
                color = Color.Red,
                cornerRadius = CornerRadius(halfRadius, halfRadius)
            )
        }
    }


}

@Preview
@Composable
fun PreviewCouponUI() {
    LearningComposeTheme {
        val animatedGradientBrush = Brush.linearGradient(
            colors = listOf(
                Color(0xff22c1c3),
                Color(0xfffdbb2d),
                Color.Red,
                Color.Magenta
            ),
        )

        CouponUI(
            modifier = Modifier
                .background(animatedGradientBrush)
                .size(200.dp)
                .padding(12.dp)
        )
    }
}