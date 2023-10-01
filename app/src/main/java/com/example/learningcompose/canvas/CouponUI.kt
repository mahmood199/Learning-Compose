package com.example.learningcompose.canvas

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun CouponUI() {

    var complete by remember {
        mutableStateOf(false)
    }

    val progress by animateFloatAsState(
        targetValue = if (complete) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearEasing
        ),
        label = "Some Animation"
    )

    Box(
        modifier = Modifier
            .size(400.dp)
            .padding(20.dp)
            .clip(RectangleShape)
            .clickable {
                complete = !complete
            }
    ) {

        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxHeight()
                .fillMaxWidth(fraction = progress)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val path = remember {
                Path().apply {

                }
            }
            Canvas(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                val width = size.width
                val height = size.width

                val path1 = Path().apply {
                    addRoundRect(
                        RoundRect(
                            Rect(
                                offset = Offset(x = width * 0.25f, y = height * 0.3f),
                                size = Size(width = width * 0.1f, height * 0.1f)
                            ),
                            10f,
                            10f
                        )
                    )
                    close()
                }

                val path2 = Path().apply {
                    addRoundRect(
                        RoundRect(
                            Rect(
                                offset = Offset(x = width * 0.75f, y = height * 0.3f),
                                size = Size(width = width * 0.1f, height * 0.1f)
                            ),
                            10f,
                            10f
                        )
                    )
                    close()
                }

                path1.addPath(path2)

                clipPath(path = path1, clipOp = ClipOp.Difference) {
                    drawRoundRect(
                        color = Color.Blue,
                        size = Size(width * 0.8f, height * 0.8f),
                        topLeft = Offset(width * 0.1f, height * 0.1f),
                        cornerRadius = CornerRadius(width * 0.05f, width * 0.05f)
                    )
                }


            }
        }
    }

}

@Preview
@Composable
fun PreviewCouponUI() {
    LearningComposeTheme {
        CouponUI()
    }
}