/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable.toolbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color

@ExperimentalMaterial3Api
@Composable
fun CollapsingToolbar(
    expandedTitle: Title,
    collapsedTitle: Title,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    expandedContent: (@Composable () -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    Surface(
        color = containerColor,
        modifier = modifier,
    ) {
        CollapsingToolbarLayout(
            expandedTitle = expandedTitle,
            collapsedTitle = collapsedTitle,
            navigationIcon = navigationIcon,
            expandedContent = expandedContent,
            collapsedProgress = scrollBehavior.state.collapsedFraction,
            heightOffset = scrollBehavior.state.heightOffset,
            onHeightOffsetLimitChange = { heightOffsetLimit ->
                scrollBehavior.state.heightOffsetLimit = heightOffsetLimit
            },
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds()
        )
    }
}
