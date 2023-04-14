@file:OptIn(ExperimentalMaterialApi::class)

package com.example.learningcompose.ui.theme.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningcompose.R
import com.example.learningcompose.ui.theme.Shapes
import kotlin.math.exp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard() {
    var expandedState by remember {
        mutableStateOf(false)
    }

    val rotationState by animateFloatAsState(
        targetValue =
        if (expandedState) 180f else 0f
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .animateContentSize(
            animationSpec = tween(
                500,
                easing = LinearOutSlowInEasing
            )
        ), shape = Shapes.small,
        onClick = {
            expandedState = !expandedState
        }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Title Text",
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(7f)
                        .wrapContentSize()
                        .fillMaxWidth(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down"
                    )
                }
            }

            if (expandedState) {
                Text(
                    text = stringResource(R.string.demo_text),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                )
            }
        }

    }
}


@Preview(showSystemUi = false)
@Composable
fun ExpandableCardPreview() {
    ExpandableCard()
}