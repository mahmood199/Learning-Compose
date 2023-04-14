package com.example.learningcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningcompose.ui.theme.LearningComposeTheme
import com.example.learningcompose.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                // A surface container using the 'background' color from the theme
                Texting()
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


@Composable
fun Texting() {
    SelectionContainer {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .background(Color.Yellow)
                    .padding(16.dp)
                    .fillMaxWidth(),
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
            DisableSelection {
                Text(
                    text = "Hehe... Not selectable",
                    modifier = Modifier
                        .background(Color.Yellow)
                        .padding(16.dp)
                        .wrapContentSize(),
                )
            }
            DisableSelection {
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        )
                    ) {
                        append("x")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = MaterialTheme.typography.overline.fontSize,
                            fontWeight = FontWeight.Normal,
                            baselineShift = BaselineShift.Subscript
                        )
                    ) {
                        append("2")
                    }
                })

            }

        }
    }
}

@Composable
fun SuperScriptText(
    normalText: String,
    superText: String,
) {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
            )
        ) {
            append(normalText)
        }
        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.overline.fontSize,
                fontWeight = FontWeight.Normal,
                baselineShift = BaselineShift.Subscript
            )
        ) {
            append(superText)
        }
    })
}

@Composable
fun SubScriptText() {
    DisableSelection {
        Text(buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                )
            ) {
                append("12")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.overline.fontSize,
                    fontWeight = FontWeight.Normal,
                    baselineShift = BaselineShift.Subscript
                )
            ) {
                append("2")
            }
        })

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningComposeTheme {
        Column {
            Texting()
            SuperScriptText(normalText = "12", superText = "2")
            SubScriptText()
        }
    }
}