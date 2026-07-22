/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import shum.oks.lab.core.ui.modifiers.shimmer
import shum.oks.lab.core.ui.preview.AnimeThemePreview
import shum.oks.lab.core.ui.preview.ThemePreviews

@Composable
internal fun CatalogSkeleton(
    minCellSize: Dp,
    itemHeight: Dp,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minCellSize),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = WindowInsets.statusBars.asPaddingValues(),
        userScrollEnabled = false,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
    ) {
        items(count = MAX_SKELETON_ITEMS) {
            CatalogSkeletonItem(itemHeight)
        }
    }
}

@Composable
private fun CatalogSkeletonItem(
    itemHeight: Dp,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .weight(1f)
                .shimmer(alpha = 1f),
        )
        repeat(2) {
            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .height(
                        with(LocalDensity.current) {
                            MaterialTheme.typography.bodyLarge.lineHeight.toDp()
                        }
                    )
                    .clip(MaterialTheme.shapes.large)
                    .shimmer(alpha = 1f)
            )
        }
    }
}

@ThemePreviews
@Composable
private fun CatalogSkeletonPreview() {
    AnimeThemePreview() {
        CatalogSkeleton(
            modifier = Modifier.fillMaxSize(),
            minCellSize = 150.dp,
            itemHeight = 200.dp,
        )
    }
}

private const val MAX_SKELETON_ITEMS = 40