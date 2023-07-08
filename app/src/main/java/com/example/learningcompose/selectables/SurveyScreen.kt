package com.example.learningcompose.selectables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.R
import com.example.learningcompose.isScrolledToTheLast
import com.example.learningcompose.selectables.models.Answer
import com.example.learningcompose.ui.theme.LearningComposeTheme
import kotlinx.coroutines.launch

@Composable
fun SurveyScreen(
    viewModel: SelectablesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val lazyListState = rememberLazyListState()
    SurveyOptionsList(viewModel.answers, lazyListState) {
        viewModel.remove(it)
    }
}

@Composable
fun SurveyOptionsList(
    listOf: List<Answer>,
    lazyListState: LazyListState,
    removeAnswer: (Answer) -> Unit
) {
    var selectedAnswer by rememberSaveable {
        mutableStateOf<Answer?>(null)
    }
    if (listOf.isEmpty()) {
        Text(
            text = "No options to select answers",
            modifier = Modifier
                .fillMaxSize()
        )
    } else {
        Box {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                content = {
                    itemsIndexed(listOf) { index, answer ->
                        SurveyAnswer(
                            answer = answer,
                            isSelected = selectedAnswer == answer,
                            answerClicked = {
                                selectedAnswer = it
                            },
                            removeAnswer = {
                                removeAnswer(answer)
                            }
                        )
                    }
                }
            )

            val scope = rememberCoroutineScope()
            if (lazyListState.isScrolledToTheLast()) {
                JumpToBottom(
                    onClicked = {
                        scope.launch {
                            lazyListState.animateScrollToItem(0) // UI logic being applied to lazyListState
                        }
                    }, modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun JumpToBottom(
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(onClick = {
        onClicked()
    }, modifier = modifier) {

    }
}

@Composable
fun SurveyAnswer(
    answer: Answer,
    isSelected: Boolean,
    answerClicked: (Answer) -> Unit,
    removeAnswer: (Answer) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                BorderStroke(
                    1.dp,
                    Color.Red
                ), shape = RoundedCornerShape(8.dp)
            )
            .padding(end = 20.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(Color.White)
            .clickable {
                answerClicked(answer)
            }
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
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
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "RemoveIcon",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    removeAnswer(answer)
                }
        )
    }

}


@Preview(
    showSystemUi = true, showBackground = true,
    device = "id:pixel_6_pro"
)
@Composable
fun SurveyPreview() {
    LearningComposeTheme {
        SurveyScreen()
    }
}