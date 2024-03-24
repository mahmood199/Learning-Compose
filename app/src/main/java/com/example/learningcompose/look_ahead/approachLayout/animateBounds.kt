package com.example.learningcompose.look_ahead.approachLayout

import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.approachLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.round

context (LookaheadScope)
@OptIn(ExperimentalAnimatableApi::class, ExperimentalComposeUiApi::class)
fun Modifier.animateBounds(): Modifier = composed {

    val offsetAnim = remember { DeferredTargetAnimation(IntOffset.VectorConverter) }

    val sizeAnim = remember { DeferredTargetAnimation(IntOffset.VectorConverter) }

    val scope = rememberCoroutineScope()

    approachLayout(
        isMeasurementApproachComplete = {
            sizeAnim.updateTarget(it.center, scope, tween(2000))
            sizeAnim.isIdle
        },
        isPlacementApproachComplete = {
            val target = lookaheadScopeCoordinates.localLookaheadPositionOf(it)
            offsetAnim.updateTarget(target.round(), scope, tween(5000))
            offsetAnim.isIdle
        }
    ) { measurable, constraints ->
        val (animWidth, animHeight) = sizeAnim.updateTarget(lookaheadSize.center, scope)

        measurable.measure(Constraints.fixed(animWidth, animHeight))
            .run {
                layout(width, height) {
                    coordinates?.let {
                        val target = lookaheadScopeCoordinates.localLookaheadPositionOf(it).round()
                        val animOffset = offsetAnim.updateTarget(target, scope, spring())
                        val current = lookaheadScopeCoordinates.localPositionOf(it, Offset.Zero).round()
                        val (x, y) = animOffset - current
                        place(x, y)
                    } ?: place(0, 0)
                }
            }
    }
}