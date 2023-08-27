package com.example.learningcompose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun ComposeA(
    text: String,
    navigateToOtherScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Button(
            onClick = navigateToOtherScreen,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = text)
        }
    }
}


@Preview
@Composable
fun ComposeAPreview() {
    LearningComposeTheme {
        ComposeA("Initial Argument") {

        }
    }
}