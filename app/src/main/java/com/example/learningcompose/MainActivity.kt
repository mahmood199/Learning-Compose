package com.example.learningcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
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
                Boxing()
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
fun TestPreview3() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Surface(
            color = Color.Red,
            modifier = Modifier
                .width(50.dp)
                .height(200.dp)
        ) {}
        CustomItem(
            color = Color.Green
        )
        CustomItem(
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
            .fillMaxSize()
            .padding(10.dp)
            .weight(weight)
    ) {}
}

@Composable
fun RowScope.CustomItem(color: Color) {
    Surface(
        color = color,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .weight(1f)
    ) {}
}

@Composable
fun StevdzaSanVideoThumbnail() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        ) {
            CustomItem(weight = 1f, color = Color.Red)
            CustomItem(weight = 1f, color = Color.Blue)
            CustomItem(weight = 1f, color = Color.Yellow)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            CustomItem(color = Color.Red)
            CustomItem(color = Color.Blue)
            CustomItem(color = Color.Yellow)
        }
    }
}

@Composable
fun Boxing() {
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hi there Mahmood. Six startups experience." +
                        " Kidding like a kid. This ain't scrollable????." +
                        " We need to make it that way then",
                style = Typography.h4,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningComposeTheme {
        Boxing()
    }
}