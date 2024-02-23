package com.example.learningcompose.story_progress_bar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun StoriesUI() {
    val bgColors = remember {
        listOf(
            Color.Gray,
            Color.Blue,
            Color.Yellow,
            Color.Green,
            Color.Magenta,
        )
    }
    var currentStep = remember {
        0
    }
    val paused = remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    bgColors[currentStep]
                ).pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            
                        }
                    )
                }
        )
        AnimatedProgressBarRow(
            modifier = Modifier.padding(16.dp),
            paused = paused.value
        ) {
            currentStep++
        }
    }
}


@Preview
@Composable
fun StoriesUIPreview() {
    LearningComposeTheme {
        StoriesUI()
    }
}

@Composable
fun AnimatedProgressBarRow(
    modifier: Modifier = Modifier,
    steps: Int = 5,
    currentStep: Int = 2,
    paused: Boolean,
    onFinished: () -> Unit = {}
) {
    val mutableCurrentStep = remember {
        currentStep
    }
    val percent = remember { mutableStateOf(Animatable(0f)) }
    LaunchedEffect(paused) {
        if (paused) percent.value.stop()
        else {
            percent.value.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 5000,
                    easing = LinearEasing
                )
            )
            percent.value = Animatable(0f)
            onFinished()
            mutableCurrentStep.inc()
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        for (index in 1..steps) { // (1)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Red)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Color.White)
                        .let {
                            // Here we check if we need to fill the progress bar of not :
                            when (index) { // (2)
                                mutableCurrentStep -> it.fillMaxWidth(percent.value.value)
                                in 0..mutableCurrentStep -> it.fillMaxWidth(1f)
                                else -> it
                            }
                        },
                ) {}
            }
        }
    }
}

