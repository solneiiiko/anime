/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.lerp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.util.lerp


@Composable
internal fun CollapsingToolbarLayout(
    expandedTitle: Title,
    collapsedTitle: Title,
    collapsedProgress: Float,
    heightOffset: Float,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    expandedContent: (@Composable () -> Unit)? = null,
    onHeightOffsetLimitChange: (Float) -> Unit,
) {
    val collapsingTitleScale = lerp(
        start = expandedTitle.textStyle,
        stop = collapsedTitle.textStyle,
        fraction = collapsedProgress
    )
    Layout(
        content = {
            Text(
                modifier = Modifier
                    .layoutId(CollapsedTitleLayoutId),
                text = collapsedTitle.text,
                style = collapsedTitle.textStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier
                    .layoutId(ExpandedTitleLayoutId),
                text = expandedTitle.text,
                style = expandedTitle.textStyle,
            )

            if (navigationIcon != null) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .layoutId(NavigationIconLayoutId)
                ) {
                    navigationIcon()
                }
            }
            if (expandedContent != null) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .layoutId(ExpandedContentLayoutId)
                        .graphicsLayer {
                            alpha = expandedContentAlpha(progress = collapsedProgress)
                        }
                ) {
                    expandedContent()
                }
            }
        },
        modifier = modifier,
    ) { measurables, constraints ->
        val navigationIconPlaceable = measurables
            .firstOrNull { it.layoutId == NavigationIconLayoutId }
            ?.measure(constraints.copy(minWidth = 0))

        val collapsedTitlePaddingPx = calculateTitlePaddingsPx(
            titlePlacement = collapsedTitle.placement,
        )
        val collapsedTitlePlaceable = measurables
            .first { it.layoutId == CollapsedTitleLayoutId }
            .measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = constraints.maxWidth - (navigationIconPlaceable?.width ?: 0) - collapsedTitlePaddingPx.horizontalPaddingPx
                )
            )

        val expandedTitlePaddingPx = calculateTitlePaddingsPx(
            titlePlacement = expandedTitle.placement,
        )
        val expandedTitlePlaceable = measurables
            .first { it.layoutId == ExpandedTitleLayoutId }
            .measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = constraints.maxWidth - expandedTitlePaddingPx.horizontalPaddingPx,
                )
            )

        val expandedContentPlaceable = measurables
            .firstOrNull { it.layoutId == ExpandedContentLayoutId }
            ?.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                )
            )

        val collapsedToolbarHeightPx = maxOf(
            navigationIconPlaceable?.height ?: 0,
            collapsedTitlePlaceable.height + collapsedTitlePaddingPx.verticalPaddingPx,
        )

        val navIconX = 0
        val navIconY = (collapsedToolbarHeightPx - (navigationIconPlaceable?.height ?: 0)) / 2

        val collapsedTitleX = (navigationIconPlaceable?.width ?: 0) + collapsedTitlePaddingPx.left
        val collapsedTitleY = when (collapsedTitle.placement) {
            is TitlePlacement.CenterVertically -> (collapsedToolbarHeightPx - collapsedTitlePlaceable.height) / 2
            is TitlePlacement.Padded -> collapsedTitlePaddingPx.top
        }

        val expandedTitleX = expandedTitlePaddingPx.left
        val expandedTitleY = collapsedToolbarHeightPx + expandedTitlePaddingPx.top

        val expandedTitleHeightPx = expandedTitlePlaceable.height + expandedTitlePaddingPx.verticalPaddingPx
        val fullToolbarHeightPx = collapsedToolbarHeightPx + expandedTitleHeightPx + (expandedContentPlaceable?.height ?: 0)

        onHeightOffsetLimitChange(-(fullToolbarHeightPx - collapsedToolbarHeightPx).toFloat())

        val layoutHeightPx = (fullToolbarHeightPx + heightOffset)
            .coerceIn(collapsedToolbarHeightPx.toFloat(), fullToolbarHeightPx.toFloat())

        val titleX = lerp(expandedTitleX, collapsedTitleX, collapsedProgress)
        val titleY = lerp(expandedTitleY, collapsedTitleY, collapsedProgress)

        layout(
            width = constraints.maxWidth,
            height = layoutHeightPx.toInt(),
        ) {
            navigationIconPlaceable?.placeRelative(
                x = navIconX,
                y = navIconY,
            )
            collapsedTitlePlaceable.placeRelativeWithLayer(
                x = titleX,
                y = titleY,
            ) {
                alpha = collapsedProgress
                scaleX = collapsingTitleScale.fontSize.value / collapsedTitle.textStyle.fontSize.value
                scaleY = collapsingTitleScale.fontSize.value / collapsedTitle.textStyle.fontSize.value
                transformOrigin = TransformOrigin(0f, 0f)
            }

            expandedTitlePlaceable.placeRelativeWithLayer(
                x = titleX,
                y = titleY,
            ) {
                alpha = 1f - collapsedProgress
                scaleX = collapsingTitleScale.fontSize.value / expandedTitle.textStyle.fontSize.value
                scaleY = collapsingTitleScale.fontSize.value / expandedTitle.textStyle.fontSize.value
                transformOrigin = TransformOrigin(0f, 0f)
            }

            expandedContentPlaceable?.placeRelativeWithLayer(
                x = 0,
                y = collapsedToolbarHeightPx + expandedTitlePlaceable.height + expandedTitlePaddingPx.verticalPaddingPx
            ) {
                translationY = -expandedContentPlaceable.height * collapsedProgress * 0.3f // TODO 0.12f bigger???
            }
        }
    }
}

private fun expandedContentAlpha(
    progress: Float,
): Float {
    return (1f - progress / ExpandedContentFadeEnd).coerceIn(0f, 1f)
}
private const val ExpandedContentFadeEnd = 0.75f

private fun MeasureScope.calculateTitlePaddingsPx(
    titlePlacement: TitlePlacement,
): TitlePaddingPx {
    val titlePaddingValues = when (titlePlacement) {
        is TitlePlacement.CenterVertically -> titlePlacement.run {
                PaddingValues(
                    start = start,
                    end = end,
                )
        }
        is TitlePlacement.Padded -> titlePlacement.paddingValues
    }
    return TitlePaddingPx(
        left = titlePaddingValues.calculateLeftPadding(layoutDirection).roundToPx(),
        right = titlePaddingValues.calculateRightPadding(layoutDirection).roundToPx(),
        top = titlePaddingValues.calculateTopPadding().roundToPx(),
        bottom = titlePaddingValues.calculateBottomPadding().roundToPx(),
    )
}

private data class TitlePaddingPx(
    val left: Int,
    val right: Int,
    val top: Int,
    val bottom: Int,
) {
    val horizontalPaddingPx = left + right
    val verticalPaddingPx = top + bottom
}

private const val CollapsedTitleLayoutId = "HeroHeaderCollapsedTitleLayoutId"
private const val ExpandedTitleLayoutId = "HeroHeaderExpandedTitleLayoutId"
private const val NavigationIconLayoutId = "HeroHeaderNavigationIconLayoutId"
private const val ExpandedContentLayoutId = "HeroHeaderExpandedContentLayoutId"
