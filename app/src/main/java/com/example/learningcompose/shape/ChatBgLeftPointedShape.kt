package com.example.learningcompose.shape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ChatBgLeftPointedShape : Shape {
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
        val startPadding = 25f
        return Path().apply {
            reset()
            moveTo(0f, 0f)

            lineTo(x = width - corner, y = 0f)

            arcTo(
                rect = Rect(
                    topLeft = Offset(x = width - doubleCorner, y = 0f),
                    bottomRight = Offset(x = width, y = doubleCorner)
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(x = width, y = doubleCorner)

            lineTo(x = width, y = height - doubleCorner)

            arcTo(
                rect = Rect(
                    topLeft = Offset(x = width - doubleCorner, y = height - doubleCorner),
                    bottomRight = Offset(x = width, y = height)
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(x = width - doubleCorner, y = height)

            lineTo(x = doubleCorner, y = height)

            arcTo(
                rect = Rect(
                    topLeft = Offset(x = startPadding, y = height - doubleCorner),
                    bottomRight = Offset(x = startPadding + doubleCorner, y = height)
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(x = startPadding, y = height - doubleCorner)

            lineTo(x = startPadding, y = corner)

            close()
        }
    }
}