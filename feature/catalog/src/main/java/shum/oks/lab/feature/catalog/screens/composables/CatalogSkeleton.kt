/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CatalogSkeleton(
    modifier: Modifier,
) {
    Text(
        modifier = modifier,
        text = "I'm SKELETON",
    )
}
