/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.modifiers

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.shimmer(
    baseColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    highlightColor: Color = MaterialTheme.colorScheme.surface,
    alpha: Float = 0.6f,
    widthFraction: Float = 0.3f,
): Modifier {
    val transition = rememberInfiniteTransition()

    val progress by transition.animateFloat(
        initialValue = SHIMMER_START_VALUE,
        targetValue = SHIMMER_END_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = ANIMATION_DURATION_MILLIS,
                easing = LinearEasing,
            ),
        ),
    )
    val colors = listOf(
        baseColor.copy(alpha = alpha),
        highlightColor.copy(alpha = alpha),
        baseColor.copy(alpha = alpha),
    )

    return this
        .drawWithCache {
            val shimmerWidth = size.width * widthFraction
            val startX = size.width * progress

            val brush = Brush.linearGradient(
                colors = colors,
                start = Offset(
                    x = startX - shimmerWidth,
                    y = size.height,
                ),
                end = Offset(
                    x = startX + shimmerWidth,
                    y = size.height,
                ),
            )

            onDrawBehind {
                drawRect(
                    color = colors.first(),
                )
                drawRect(
                    brush = brush,
                )
            }
        }
}

private const val ANIMATION_DURATION_MILLIS = 1_200
private const val SHIMMER_START_VALUE = -1f
private const val SHIMMER_END_VALUE = 2f
