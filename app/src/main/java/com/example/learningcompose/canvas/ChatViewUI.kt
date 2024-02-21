package com.example.learningcompose.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Preview
@Composable
private fun ChatViewUI() {
    LearningComposeTheme {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item("right_chat_1") {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clip(ChatBgRightPointedShape())
                            .background(Color.Red)
                            .padding(start = 8.dp, end = 16.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Prompt demo for chat view")
                    }
                }
            }

            item("left_chat_1") {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .clip(ChatBgLeftPointedShape())
                            .background(Color.Red)
                            .padding(start = 16.dp, end = 8.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Some text from me")
                    }
                }
            }

            item("right_chat_2") {
                Box(modifier = Modifier.fillMaxWidth()) {
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
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .clip(ChatBgLeftPointedShape())
                            .background(Color.Red)
                            .padding(start = 16.dp, end = 8.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Some text from me")
                    }
                }
            }

        }
    }
}