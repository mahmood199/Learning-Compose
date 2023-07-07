package com.example.learningcompose.state

import androidx.annotation.Dimension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Badge
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme
import kotlin.math.max

@Composable
fun LearningSizes(
    images: List<String>,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val boxWithConstraintsScope = this
        val padding = Dimension(2).unit
        val thumbnailSize = Dimension(6).unit

        val numberOfItemsToShow = max(
            0,
            boxWithConstraintsScope.maxWidth.div(padding + thumbnailSize).value.toInt().minus(1)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(padding.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            images.take(numberOfItemsToShow).forEach {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Red)
                        .aspectRatio(1f)
                ) {

                }
            }

            val remaining = images.size - numberOfItemsToShow
            if (remaining > 0) {
                Badge(
                    contentColor = Color.Blue,
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.Blue)
                ) {
                    Text(text = remaining.toString())
                }
            }

        }

    }
}


@Preview
@Composable
fun LearningSizesPreview() {
    LearningComposeTheme {
        LearningSizes(images)
    }
}


val images = buildList {
    add("String 1")
    add("String 2")
    add("String 3")
    add("String 4")
    add("String 5")
    add("String 6")
    add("String 7")
}