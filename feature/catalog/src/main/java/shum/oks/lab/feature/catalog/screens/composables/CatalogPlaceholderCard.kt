/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shum.oks.lab.core.ui.modifiers.shimmer

@Composable
internal fun CatalogPlaceholderCard() {
    // TODO https://github.com/solneiiiko/anime/issues/31
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shimmer(),
        contentAlignment = Alignment.Center
    ) {

    }
}
