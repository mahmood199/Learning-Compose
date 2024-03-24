package com.example.learningcompose.look_ahead.ApproachLayoutModifierNode

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LookAheadUI(
    modifier: Modifier = Modifier
) {
    val colors = remember {
        listOf(
            Color(0xffff6f69),
            Color(0xffffcc5c),
            Color(0xff264653),
            Color(0xFF679138),
        )
    }

    var isInColumn by remember { mutableStateOf(true) }

    LookaheadScope {
        val items = remember {
            movableContentOf {
                colors.forEach { color ->
                    Box(
                        Modifier
                            .padding(8.dp)
                            .size(80.dp)
                            .animatePlacementInScope(this)
                            .background(color, RoundedCornerShape(10))
                    )
                }
            }
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .clickable { isInColumn = !isInColumn }
        ) {
            if (isInColumn) {
                Column {
                    items()
                }
            } else {
                Row {
                    items()
                }
            }
        }
    }
}

@Preview
@Composable
fun LookAheadUIPreview() {
    LearningComposeTheme {
        LookAheadUI()
    }
}