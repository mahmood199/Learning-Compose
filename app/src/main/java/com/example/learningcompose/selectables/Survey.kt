package com.example.learningcompose.selectables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.R
import com.example.learningcompose.selectables.models.Answer
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun Survey(listOf: List<Answer>) {

    val selectedAnswer: MutableState<Answer?> = rememberSaveable {
        mutableStateOf(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (listOf.isEmpty()) {
            Text(
                text = "No options to select answers",
                modifier = Modifier
                    .fillMaxSize()
            )
        } else {
            listOf.forEach { answer ->
                SurveyAnswer(
                    answer = answer,
                    isSelected = selectedAnswer.value == answer
                ) {
                    selectedAnswer.value = it
                }
            }
        }
    }
}

@Composable
fun SurveyAnswer(
    answer: Answer,
    isSelected: Boolean,
    answerClicked: (Answer) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(Color.White)
            .clickable {
                answerClicked(answer)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(
                id = R.drawable.ic_launcher_background
            ),
            contentDescription = "Default${answer.id}"
        )
        Text(
            text = answer.text,
            modifier = Modifier.weight(1f)
        )
        RadioButton(
            selected = isSelected,
            onClick = {
                answerClicked(answer)
            }
        )
    }
}


@Preview
@Composable
fun SurveyPreview() {
    LearningComposeTheme {
        Survey(
            listOf(
                Answer(0, "First Answer", true),
                Answer(1, "Second Answer", false),
                Answer(2, "Third Answer", false),
                Answer(3, "Fourth Answer", false),
            )
        )
    }
}