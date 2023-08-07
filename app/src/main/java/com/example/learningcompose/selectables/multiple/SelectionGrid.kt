package com.example.learningcompose.selectables.multiple

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.onLongClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.toIntRect
import com.example.learningcompose.ui.theme.LearningComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun SelectionGrid(
    modifier: Modifier = Modifier
) {
    val photos by rememberSaveable {
        mutableStateOf(List(100) {
            it
        })
    }
    val selectedIds = rememberSaveable {
        mutableStateOf(emptyList<Int>())
    }
    val inSelectionMode by remember {
        derivedStateOf {
            selectedIds.value.isNotEmpty()
        }
    }
    val state = rememberLazyGridState()
    val autoScrollSpeed = remember { mutableStateOf(0f) }
    // Executing the scroll
    LaunchedEffect(autoScrollSpeed.value) {
        if (autoScrollSpeed.value != 0f) {
            while (isActive) {
                state.scrollBy(autoScrollSpeed.value)
                delay(10)
            }
        }
    }
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.photoGridDragHandler(
            lazyGridState = state,
            selectedIds = selectedIds,
            autoScrollSpeed = autoScrollSpeed,
            autoScrollThreshold = with(LocalDensity.current) { 40.dp.toPx() }
        )
    ) {
        items(photos, key = { it }) { id ->
            val selected = selectedIds.value.contains(id)
            ImageItem(
                selected = selected,
                inSelectionMode = inSelectionMode,
                modifier = Modifier
                    .semantics {
                        if (!inSelectionMode) {
                            onLongClick("Select") {
                                selectedIds.value += id
                                true
                            }
                        }
                    }
                    .then(
                        if (inSelectionMode)
                            Modifier.toggleable(
                                value = selected,
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null,
                                onValueChange = {
                                    if (it) {
                                        selectedIds.value += id
                                    } else {
                                        selectedIds.value -= id
                                    }
                                }
                            )
                        else Modifier
                    )
            )
        }
    }
}

@Composable
fun ImageItem(
    selected: Boolean,
    inSelectionMode: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier.aspectRatio(1f)
    ) {

        Box {
            val transition = updateTransition(selected, label = "selected")
            val padding by transition.animateDp(label = "padding") { selected ->
                if (selected) 10.dp else 0.dp
            }
            val roundedCornerShape by transition.animateDp(label = "corner") { selected ->
                if (selected) 16.dp else 0.dp
            }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Gray)
                    .clip(RoundedCornerShape(roundedCornerShape.value))
            )

        }

        if (inSelectionMode) {
            if (selected) {
                val bgColor = MaterialTheme.colors.surface
                Icon(
                    Icons.Filled.CheckCircle,
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .border(2.dp, bgColor, CircleShape)
                        .clip(CircleShape)
                        .background(bgColor)
                )
            } else {
                Icon(
                    Icons.Filled.RadioButtonUnchecked,
                    tint = Color.White.copy(alpha = 0.7f),
                    contentDescription = null,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}

fun Modifier.photoGridDragHandler(
    lazyGridState: LazyGridState,
    selectedIds: MutableState<List<Int>>,
    autoScrollSpeed: MutableState<Float>,
    autoScrollThreshold: Float
) = pointerInput(Unit) {
    var initialKey: Int? = null
    var currentKey: Int? = null
    detectDragGesturesAfterLongPress(
        onDragStart = { offset ->
            lazyGridState.gridItemKeyAtPosition(offset)?.let { key ->
                if (!selectedIds.value.contains(key)) {
                    initialKey = key
                    currentKey = key
                    selectedIds.value = selectedIds.value + key
                }
            }
        },
        onDragCancel = { initialKey = null; autoScrollSpeed.value = 0f },
        onDragEnd = { initialKey = null },
        onDrag = { change, _ ->
            if (initialKey != null) {
                val distFromBottom =
                    lazyGridState.layoutInfo.viewportSize.height - change.position.y
                val distFromTop = change.position.y
                autoScrollSpeed.value = when {
                    distFromBottom < autoScrollThreshold -> autoScrollThreshold - distFromBottom
                    distFromTop < autoScrollThreshold -> -(autoScrollThreshold - distFromTop)
                    else -> 0f
                }

                lazyGridState.gridItemKeyAtPosition(change.position)?.let { key ->
                    if (currentKey != key) {
                        selectedIds.value = selectedIds.value
                            .minus(initialKey!!..currentKey!!)
                            .minus(currentKey!!..initialKey!!)
                            .plus(initialKey!!..key)
                            .plus(key..initialKey!!)
                        currentKey = key
                    }
                }
            }
        }
    )
}

fun LazyGridState.gridItemKeyAtPosition(hitPoint: Offset): Int? =
    layoutInfo.visibleItemsInfo.find { itemInfo ->
        itemInfo.size.toIntRect().contains(hitPoint.round() - itemInfo.offset)
    }?.key as? Int

@Preview
@Composable
fun SelectionGridPreview() {
    LearningComposeTheme {
        SelectionGrid()
    }
}