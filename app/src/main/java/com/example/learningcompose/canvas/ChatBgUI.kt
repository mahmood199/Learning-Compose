package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun ChatBgUI(
    isLeft: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            val width = size.width
            val height = size.height

            val minDimension = minOf(width, height)
            val halfRadius = minDimension * .125f * 0.5f
            drawRoundRect(Color.Red, cornerRadius = CornerRadius(20f, 20f))
        }

        content()

    }

}

@Preview
@Composable
private fun ChatBgUIPreview() {
    LearningComposeTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            ChatBgUI(isLeft = false, modifier = Modifier) {
                Text(text = "Some text")
            }
        }
    }
}