package com.example.learningcompose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun ComposeB(
    parameter: String?,
    navigateToRoot: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = parameter ?: "",
            style = MaterialTheme.typography.h1
        )
        Button(onClick = navigateToRoot) {
            Text(text = "Root")
        }
    }
}


@Preview
@Composable
fun ComposeBPreview() {
    LearningComposeTheme {
        ComposeB("Mahmood") {

        }
    }
}