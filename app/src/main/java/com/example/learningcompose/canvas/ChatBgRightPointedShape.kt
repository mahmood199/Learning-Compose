package com.example.learningcompose.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

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

@Preview
@Composable
private fun ChatBgShapeIllustration2() {
    LearningComposeTheme {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            item("right_chat_1") {
                Column(
                    modifier = Modifier
                        .clip(ChatBgRightPointedShape())
                        .background(Color.Red)
                        .padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Text(text = "Some text from me")
                }

            }

            item("right_chat_2") {
                Column(
                    modifier = Modifier
                        .clip(ChatBgRightPointedShape())
                        .background(Color.Red)
                        .padding(start = 8.dp, end = 16.dp)
                ) {
                    Text(text = "Some text from me Some text from me Some text from me Some text from me")
                }

            }

        }
    }
}
