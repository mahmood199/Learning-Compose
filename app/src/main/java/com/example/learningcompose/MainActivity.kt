package com.example.learningcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme
import com.example.learningcompose.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    TestPreview1("Android")
                }
            }
        }
    }
}

@Composable
fun TestPreview1(
    name: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp), color = Color.Red
        ) {}
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp), color = Color.Green
        ) {}
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp), color = Color.Blue
        ) {}

    }
}

@Composable
fun TestPreview2() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            color = Color.Red,
            modifier = Modifier
                .width(200.dp)
                .weight(2f)
        ) {}
        CustomItem(
            1f,
            color = Color.Green
        )
        CustomItem(
            1f,
            color = Color.Blue
        )
    }
}

@Composable
fun ExpandableCard(title: String, description: String) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(text = title)

            if (expanded) {
                Text(
                    text = description,
                )
                IconButton(onClick = {
                    expanded = false
                }) {}
            } else {
                IconButton(onClick = {
                    expanded = true
                }) {}
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
            text = "Id here", style = Typography.h3

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

@Composable
fun ColumnScope.CustomItem(weight: Float, color: Color) {
    Surface(
        color = color,
        modifier = Modifier
            .width(200.dp)
            .weight(weight)
    ) {}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningComposeTheme {
        TestPreview2()
    }
}