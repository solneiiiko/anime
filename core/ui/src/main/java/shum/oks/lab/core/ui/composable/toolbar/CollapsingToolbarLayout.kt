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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.util.lerp


@Composable
internal fun CollapsingToolbarLayout(
    expandedTitle: Title,
    collapsedTitle: Title,
    collapsedProgress: () -> Float,
    heightOffset: () -> Float,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    expandedContent: (@Composable () -> Unit)? = null,
    onHeightOffsetLimitChange: (Float) -> Unit,
) {
    val animatedCollapsedTextStyle = remember(collapsedTitle) {
        collapsedTitle.textStyle.copy(textMotion = TextMotion.Animated)
    }
    val animatedExpandedTextStyle = remember(expandedTitle) {
        expandedTitle.textStyle.copy(textMotion = TextMotion.Animated)
    }
    Layout(
        content = {
            Text(
                modifier = Modifier
                    .layoutId(CollapsedTitleLayoutId)
                    .testTag(CollapsedTitleTestTag),
                text = collapsedTitle.text,
                style = animatedCollapsedTextStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier
                    .layoutId(ExpandedTitleLayoutId)
                    .testTag(ExpandedTitleTestTag),
                text = expandedTitle.text,
                style = animatedExpandedTextStyle,
            )

            if (navigationIcon != null) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .layoutId(NavigationIconLayoutId)
                        .testTag(NavigationIconTestTag),
                ) {
                    navigationIcon()
                }
            }
            if (expandedContent != null) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .layoutId(ExpandedContentLayoutId)
                        .testTag(ExpandedContentTestTag),
                ) {
                    expandedContent()
                }
            }
        },
        modifier = modifier,
    ) { measurables, constraints ->
        val measurablesMap = measurables.associateBy { it.layoutId }
        val navigationIconPlaceable = measurablesMap[NavigationIconLayoutId]
            ?.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                ),
            )
        val collapsedTitlePaddingPx = calculateTitlePaddingsPx(
            titlePlacement = collapsedTitle.placement,
        )
        val collapsedTitlePlaceable = measurablesMap.getValue(CollapsedTitleLayoutId)
            .measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = (
                        constraints.maxWidth - (navigationIconPlaceable?.width ?: 0)
                                - collapsedTitlePaddingPx.horizontalPaddingPx
                    ).coerceAtLeast(0),
                ),
            )
        val expandedTitlePaddingPx = calculateTitlePaddingsPx(
            titlePlacement = expandedTitle.placement,
        )
        val expandedTitlePlaceable = measurablesMap.getValue(ExpandedTitleLayoutId)
            .measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = (constraints.maxWidth - expandedTitlePaddingPx.horizontalPaddingPx)
                        .coerceAtLeast(0),
                )
            )
        val expandedContentPlaceable = measurablesMap[ExpandedContentLayoutId]
            ?.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                ),
            )
        val collapsedToolbarHeightPx = maxOf(
            navigationIconPlaceable?.height ?: 0,
            collapsedTitlePlaceable.height + collapsedTitlePaddingPx.verticalPaddingPx,
        )

        val expandedTitleHeightPx = expandedTitlePlaceable.height + expandedTitlePaddingPx.verticalPaddingPx
        val fullToolbarHeightPx = collapsedToolbarHeightPx + expandedTitleHeightPx + (expandedContentPlaceable?.height ?: 0)

        val heightOffsetLimit = -(fullToolbarHeightPx - collapsedToolbarHeightPx).toFloat()
        onHeightOffsetLimitChange(heightOffsetLimit)

        val layoutHeightPx = (fullToolbarHeightPx + heightOffset()).coerceIn(
                minimumValue = collapsedToolbarHeightPx.toFloat(),
                maximumValue = fullToolbarHeightPx.toFloat(),
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
        val expandedContentY = collapsedToolbarHeightPx + expandedTitleHeightPx
        val expandedFontSize = expandedTitle.textStyle.fontSize.value
        val collapsedFontSize = collapsedTitle.textStyle.fontSize.value

        layout(
            width = constraints.maxWidth,
            height = layoutHeightPx.toInt(),
        ) {
            val progress = collapsedProgress()
            val titleX = lerp(
                start = expandedTitleX,
                stop = collapsedTitleX,
                fraction = progress,
            )
            val titleY = lerp(
                start = expandedTitleY,
                stop = collapsedTitleY,
                fraction = progress,
            )
            val currentFontSize = lerp(
                start = expandedFontSize,
                stop = collapsedFontSize,
                fraction = progress,
            )

            val expandedTitleScale = currentFontSize / expandedFontSize
            val collapsedTitleScale = currentFontSize / collapsedFontSize

            navigationIconPlaceable?.placeRelative(
                x = navIconX,
                y = navIconY,
            )
            collapsedTitlePlaceable.placeRelativeWithLayer(
                x = titleX,
                y = titleY,
            ) {
                alpha = progress
                scaleX = collapsedTitleScale
                scaleY = collapsedTitleScale
                transformOrigin = TransformOrigin(0f, 0f)
            }
            expandedTitlePlaceable.placeRelativeWithLayer(
                x = titleX,
                y = titleY,
            ) {
                alpha = 1f - progress
                scaleX = expandedTitleScale
                scaleY = expandedTitleScale
                transformOrigin = TransformOrigin(0f, 0f)
            }
            expandedContentPlaceable?.placeRelativeWithLayer(
                x = 0,
                y = expandedContentY,
            ) {
                alpha = expandedContentAlpha(progress = progress)
                translationY = -expandedContentPlaceable.height * progress * ExpandedContentTranslationFactor
            }
        }
    }
}

internal fun expandedContentAlpha(
    progress: Float,
): Float {
    return (1f - progress / ExpandedContentFadeEnd).coerceIn(0f, 1f)
}

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

internal data class TitlePaddingPx(
    val left: Int,
    val right: Int,
    val top: Int,
    val bottom: Int,
) {
    val horizontalPaddingPx = left + right
    val verticalPaddingPx = top + bottom
}

internal const val ExpandedContentFadeEnd = 0.75f
private const val ExpandedContentTranslationFactor = 0.3f

private const val CollapsedTitleLayoutId = "CollapsedTitleLayoutId"
private const val ExpandedTitleLayoutId = "ExpandedTitleLayoutId"
private const val NavigationIconLayoutId = "NavigationIconLayoutId"
private const val ExpandedContentLayoutId = "ExpandedContentLayoutId"

internal const val CollapsedTitleTestTag = "CollapsedTitleTestTag"
internal const val ExpandedTitleTestTag = "ExpandedTitleTestTag"
internal const val NavigationIconTestTag = "NavigationIconTestTag"
internal const val ExpandedContentTestTag = "ExpandedContentTestTag"
