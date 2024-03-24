package com.example.learningcompose.look_ahead.ApproachLayoutModifierNode

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.VectorConverter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ApproachLayoutModifierNode
import androidx.compose.ui.layout.ApproachMeasureScope
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.round

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimatableApi::class)
class AnimatedPlacementModifierNode(
    var lookaheadScope: LookaheadScope
) : ApproachLayoutModifierNode, Modifier.Node() {

    private val offsetAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D> =
        DeferredTargetAnimation(IntOffset.VectorConverter)

    override fun isMeasurementApproachComplete(lookaheadSize: IntSize): Boolean {
        return true
    }

    @ExperimentalComposeUiApi
    override fun ApproachMeasureScope.approachMeasure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            val coordinates = coordinates
            if (coordinates != null) {
                val target: IntOffset = with(lookaheadScope) {
                    lookaheadScopeCoordinates.localLookaheadPositionOf(coordinates).round()
                }

                val animatedOffset = offsetAnimation.updateTarget(target, coroutineScope)

                val placementOffset = with(lookaheadScope) {
                    lookaheadScopeCoordinates.localPositionOf(coordinates, Offset.Zero).round()
                }

                val (x, y) = animatedOffset - placementOffset
                placeable.place(x, y)
            } else {
                placeable.place(0, 0)
            }
        }
    }
}

data class AnimatePlacementNodeElement(val lookaheadScope: LookaheadScope) :
    ModifierNodeElement<AnimatedPlacementModifierNode>() {

    override fun update(node: AnimatedPlacementModifierNode) {
        node.lookaheadScope = lookaheadScope
    }

    override fun create(): AnimatedPlacementModifierNode {
        return AnimatedPlacementModifierNode(lookaheadScope)
    }
}


fun Modifier.animatePlacementInScope(lookaheadScope: LookaheadScope): Modifier {
    return this.then(AnimatePlacementNodeElement(lookaheadScope))
}