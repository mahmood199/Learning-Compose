package com.example.learningcompose.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.shape.ChatBgLeftPointedShape
import com.example.learningcompose.shape.ChatBgRightPointedShape
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Preview
@Composable
private fun ChatViewUI() {
    LearningComposeTheme {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 20.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item("right_chat_1") {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .aspectRatio(1f)
                    )
                    Column(
                        modifier = Modifier
                            .clip(ChatBgRightPointedShape())
                            .background(Color.Red)
                            .padding(start = 8.dp, end = 16.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Prompt demo for chat view ")
                    }
                }
            }

            item("left_chat_1") {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    Column(
                        modifier = Modifier
                            .clip(ChatBgLeftPointedShape())
                            .background(Color.Red)
                            .padding(start = 16.dp, end = 8.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Some text from medasnb iapeobhy dnabiso fhyasdodm wdb ohudw uh")
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .aspectRatio(1f)
                    )
                }
            }

            item("right_chat_2") {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth(0.9f)
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .aspectRatio(1f)
                    )

                    Column(
                        modifier = Modifier
                            .clip(ChatBgRightPointedShape())
                            .background(Color.Red)
                            .padding(start = 8.dp, end = 16.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Some text from me Some text from me Some text from me Some text from me")
                    }
                }
            }


            item("left_chat_2") {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    Column(
                        modifier = Modifier
                            .clip(ChatBgLeftPointedShape())
                            .background(Color.Red)
                            .padding(start = 16.dp, end = 8.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Some text ")
                    }

                }
            }

        }
    }
}

fun LazyListScope.leftChatItem(
    key: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    item(key = key) {
        Box(
            modifier = modifier.fillMaxWidth(0.75f),
            contentAlignment = Alignment.CenterStart
        ) {
            content()
        }
    }
}

fun LazyListScope.rightChatItem(
    key: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    item(key = key) {
        Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            content()
        }
    }
}