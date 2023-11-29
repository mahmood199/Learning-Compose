package com.example.learningcompose.canvas

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun BlinkingIconUI() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val size = remember {
            40.dp
        }
        val transition = rememberInfiniteTransition("Scale transition")
        val scale by transition.animateFloat(
            initialValue = 1f,
            targetValue = 2f,
            animationSpec = InfiniteRepeatableSpec(
                tween(
                    durationMillis = 500,
                    easing = LinearEasing
                ), repeatMode = RepeatMode.Restart
            ),
            label = "Scale Animation"
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(size * scale)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.5f))
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(size)
                .clip(CircleShape)
                .background(Color.White)
        ) {

        }
    }
}


@Preview
@Composable
fun BlinkingIconUIPreview() {
    LearningComposeTheme {
        BlinkingIconUI()
    }
}