/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.modifiers

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

fun Modifier.shimmerModifier(
    alpha: Float = 0.6f,
): Modifier = composed {
    val transition = rememberInfiniteTransition()

    val translateAnim by transition.animateFloat(
        initialValue = SHIMMER_START_VALUE,
        targetValue = SHIMMER_END_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = ANIMATION_DURATION_MILLIS, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
    )

    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surfaceDim.copy(alpha = alpha),
        MaterialTheme.colorScheme.surfaceBright.copy(alpha = alpha),
        MaterialTheme.colorScheme.surfaceDim.copy(alpha = alpha)
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim - SHIMMER_OFFSET, translateAnim - SHIMMER_OFFSET),
        end = Offset(translateAnim, translateAnim)
    )

    this.background(brush)
}

@Preview(name = "check animation not theme", showBackground = true)
@Composable
private fun ShimmerModifierPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .shimmerModifier(alpha = 0.7f)
            )
        }
    }
}

private const val ANIMATION_DURATION_MILLIS = 1_200
private const val SHIMMER_START_VALUE = 0f
private const val SHIMMER_END_VALUE = 2_000f
private const val SHIMMER_OFFSET = 500f
