package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun FacebookIconUI() {

    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    ) {
        val width = size.width
        val height = size.width

        drawRoundRect(
            color = Color.Blue,
            cornerRadius = CornerRadius(
                x = width * 0.125f,
                y = height * 0.125f
            )
        )

        val result = textMeasurer.measure(
            text = "f",
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = TextUnit(96f, TextUnitType.Sp)
            ),
        )

        drawText(
            textLayoutResult = result,
            color = Color.White,
            topLeft = Offset(width * 0.5f - result.size.width / 2, 0f)
        )

    }
}


@Preview
@Composable
fun PreviewFacebookIconUI() {
    LearningComposeTheme {
        FacebookIconUI()
    }
}