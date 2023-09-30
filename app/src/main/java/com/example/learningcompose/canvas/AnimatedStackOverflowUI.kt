package com.example.learningcompose.canvas

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotateRad
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme


@Composable
fun AnimatedStackOverflowUI() {

    var done by remember {
        mutableStateOf(false)
    }

    val animatedRadian by animateFloatAsState(
        if (done)
            1.57f
        else
            0.0f,
        label = "Radian Animation",
        animationSpec = tween(
            easing = LinearEasing,
            durationMillis = 1000
        )
    )

    val animateHeight by animateFloatAsState(
        targetValue = if (done)
            0.120f
        else
            0.675f,
        label = "Height Animation",
        animationSpec = tween(
            easing = LinearEasing,
            durationMillis = 1000
        )
    )

    Canvas(
        modifier = Modifier
            .size(200.dp)
            .background(Color.DarkGray)
            .clickable {
                done = !done
            }
    ) {
        val width = size.width
        val height = size.width

        rotateRad(
            radians = animatedRadian,
            pivot = Offset(width * 1.0f, height * 0.775f)
        ) {
            drawRect(
                color = Color.Green,
                topLeft = Offset(width * 0.2f, height * animateHeight),
                size = Size(width * 0.6f, height * 0.1f)
            )
        }
    }
}


@Preview
@Composable
fun PreviewAnimatedStackOverflowUI() {
    LearningComposeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(100.dp)
        ) {
            AnimatedStackOverflowUI()
        }
    }
}