package com.example.learningcompose.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun CouponUI2(
    modifier: Modifier = Modifier
) {

    var complete by remember {
        mutableStateOf(false)
    }

    BoxWithConstraints {
        val path = remember {
            getSomePath(Size(maxWidth.value, maxHeight.value))
        }

        Canvas(
            modifier = modifier
                .fillMaxSize()
                .clickable {
                    complete = complete.not()
                }
                .zIndex(20f)
        ) {
            drawPath(path = path, color = Color.Red)
        }
    }
}

fun getSomePath(size: Size): Path {
    val halfRadius = size.width / 10f

    return Path().apply {
        reset()

        lineTo(halfRadius, 0f)




        lineTo(size.width - halfRadius, 0f)

    }
}

@Preview
@Composable
fun PreviewCouponUI2() {
    LearningComposeTheme {

        CouponUI2(
            modifier = Modifier
                .size(200.dp)
                .padding(12.dp)
        )
    }
}