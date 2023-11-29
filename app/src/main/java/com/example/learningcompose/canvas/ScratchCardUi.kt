package com.example.learningcompose.canvas

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun ScratchCardUi(
    modifier: Modifier = Modifier
) {

    var currentPathState by remember { mutableStateOf(DraggedPath(path = Path())) }
    var movedOffsetState by remember { mutableStateOf<Offset?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(5))
            .background(Color.DarkGray)
    ) {
        ScratchSurface(
            currentPath = currentPathState.path,
            currentPathThickness = currentPathState.width,
            onMovedOffset = { x: Float, y: Float ->
                movedOffsetState = Offset(x, y)
            },
            movedOffset = movedOffsetState,
        )

        IconButton(
            onClick = {
                movedOffsetState = null
                currentPathState = DraggedPath(path = Path())
            },
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Clear",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScratchSurface(
    currentPath: Path,
    movedOffset: Offset?,
    currentPathThickness: Float,
    onMovedOffset: (Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .clipToBounds()
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        println("CurrentPath/ACTION_DOWN: (${it.x}, ${it.y})")
                        currentPath.moveTo(it.x, it.y)
                    }

                    MotionEvent.ACTION_MOVE -> {
                        println("MovedOffset/ACTION_MOVE: (${it.x}, ${it.y})")
                        onMovedOffset(it.x, it.y)
                    }
                }
                true
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val imageSize = Size(width = canvasWidth, height = canvasHeight)

        movedOffset?.let {
            currentPath.addOval(oval = Rect(it, currentPathThickness))
        }

        clipPath(path = currentPath, clipOp = ClipOp.Intersect) {
            drawRect(
                color = Color.Black,
                size = imageSize
            )
        }
    }
}


@Preview
@Composable
fun PreviewScratchCardUi() {
    LearningComposeTheme {
        ScratchCardUi(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
    }
}

data class DraggedPath(
    val path: Path,
    val width: Float = 50f
)