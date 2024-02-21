package com.example.learningcompose.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.learningcompose.BottomNavScreen
import com.example.learningcompose.ui.theme.LearningComposeTheme

class MiddleBulgeShapeV2 : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width = size.width
        val height = size.height

        return Outline.Generic(
            path = drawMiddleBulgeShape(width, height)
        )
    }

    private fun drawMiddleBulgeShape(width: Float, height: Float): Path {
        val topHeight = 35f
        val circleRadiusInPx = 25f * 2
        return Path().apply {
            reset()

            moveTo(0f, topHeight)

            lineTo(width * 0.0f, topHeight)

            lineTo(width * 0.45f - circleRadiusInPx, topHeight)


            cubicTo(
                x1 = width * 0.45f - circleRadiusInPx,
                y1 = topHeight,
                x2 = width * 0.5f,
                y2 = -20f,
                x3 = width * 0.55f + circleRadiusInPx,
                y3 = topHeight
            )

            lineTo(width * 0.55f + circleRadiusInPx, topHeight)

            lineTo(width * 1.0f, topHeight)

            lineTo(width * 1.0f, height * 1.0f)

            lineTo(width * 0.0f, height * 1.0f)


            close()
        }
    }
}

@Preview
@Composable
fun MiddleBulgeShapeV2Preview() {
    LearningComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val bottomBarItem = remember {
                        buildList {
                            add(BottomNavScreen.Links)
                            add(BottomNavScreen.Courses)
                            add(BottomNavScreen.Links)
                            add(BottomNavScreen.Campaigns)
                            add(BottomNavScreen.Profile)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MiddleBulgeShapeV2())
                            .background(Color.White)
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        bottomBarItem.forEachIndexed { index, screens ->
                            when (index) {
                                2 -> {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1f)
                                    ) {
                                    }
                                }

                                else -> {
                                    BottomBarItem(
                                        screen = screens,
                                        isSelected = index % 2 == 0,
                                        onClick = {
                                        },
                                        modifier = Modifier
                                            .weight(1f)
                                    )

                                }
                            }
                        }
                    }
                }

                CustomFab(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp)
                )
            }

        }
    }
}