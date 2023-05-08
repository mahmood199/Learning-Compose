package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

class CodeLabSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.secondary
    ) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val extraPadding = if (expanded.value) 80.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(
            vertical = 4.dp,
            horizontal = 8.dp
        )
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            Button(onClick = { expanded.value = !expanded.value }) {
                Text(text = if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun LoopingText(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose", "Mahmood"),
) {
    Column(modifier = modifier.padding(vertical = 4.dp, horizontal = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun MyApp2(modifier: Modifier = Modifier) {

    var shouldShowOnboarding = remember { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding.value) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding.value = false })
        } else {
            LoopingText()
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome the newbie to Codelabs!")
        Button(
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }
}

@Preview
@Composable
fun MyAppPreview() {
    LearningComposeTheme {
        LoopingText(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LearningComposeTheme {
        LoopingText(
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LearningComposeTheme() {
        MyApp2(Modifier.fillMaxSize())
    }
}