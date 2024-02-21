package com.example.learningcompose.shape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ChatBgRightPointedShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width = size.width
        val height = size.height

        return Outline.Generic(
            path = drawChatBackground(width, height)
        )

    }

    private fun drawChatBackground(width: Float, height: Float): Path {
        val corner = 25f
        val doubleCorner = 50f
        val endPadding = 25f
        return Path().apply {
            reset()
            moveTo(x = width, y = 0f)

            lineTo(x = 0f, y = 0f)

            arcTo(
                rect = Rect(
                    topLeft = Offset(x = 0f, y = 0f),
                    bottomRight = Offset(x = doubleCorner, y = doubleCorner)
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = -90f,
                forceMoveTo = false
            )

            lineTo(x = 0f, y = height - doubleCorner)

            arcTo(
                rect = Rect(
                    topLeft = Offset(x = 0f, y = height - doubleCorner),
                    bottomRight = Offset(x = doubleCorner, y = height)
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = -90f,
                forceMoveTo = false
            )

            lineTo(x = width - endPadding, y = height)

            arcTo(
                rect = Rect(
                    topLeft = Offset(
                        x = width - endPadding - doubleCorner,
                        y = height - doubleCorner
                    ),
                    bottomRight = Offset(x = width - endPadding, y = height)
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = -90f,
                forceMoveTo = false
            )

            lineTo(x = width - endPadding, y = corner)

            close()
        }
    }
}