package com.example.learningcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.LearningComposeTheme
import com.example.learningcompose.ui.theme.Typography
import kotlin.text.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column() {
        Text(text = "Hello $name!")
        CTA()
        InputField()
        ExpandableCard("Initial Title", "Initial Description")
    }
}

@Composable
fun ExpandableCard(title: String, description: String) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card {
        Column {
            Text(text = title)

            if (expanded) {
                Text(
                    text = description,
                )
                IconButton(onClick = {
                    expanded = false
                }) {
                }
            } else {
                IconButton(onClick = {
                    expanded = true
                }) {
                }
            }
        }
    }

}

@Composable
fun InputField() {
    var name = "String"

    TextField(value = "", onValueChange = {
        name = it
    }, label = {
        Text(
            text = "Id here",
            style = Typography.h3

        )
    })

}

@Composable
fun CTA() {

    val context = LocalContext.current

    Button(onClick = {
        Toast.makeText(context, "Hey there", Toast.LENGTH_SHORT).show()
    }) {

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningComposeTheme {
        Greeting("Android")
    }
}