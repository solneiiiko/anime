/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shum.oks.lab.feature.catalog.screens.models.Anime

@Composable
internal fun AnimeItem(
    anime: Anime
) {
    Text(
        text = "${anime.id} ## ${anime.title}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
