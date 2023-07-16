package com.example.learningcompose.animation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.learningcompose.R
import com.example.learningcompose.ui.theme.LearningComposeTheme


@Composable
fun Animation(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val size by infiniteTransition.animateValue(
        initialValue = 100.dp,
        targetValue = 150.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                110.dp at 200
                120.dp at 400
                130.dp at 600
                140.dp at 800
                150.dp at 1000
            },
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(
                id = R.drawable.ic_launcher_background,
            ),
            contentDescription = "NULL",
            modifier = Modifier.size(size),
        )
    }

}

@Preview
@Composable
fun SideEffectTestPreview() {
    LearningComposeTheme() {
        Animation()
    }
}