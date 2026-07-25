/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsViewModel
import shum.oks.lab.feature.details.anime.screens.composables.AnimeDetailsContent

@Composable
internal fun AnimeDetailScreen(
    viewModel: AnimeDetailsViewModel,
    modifier: Modifier = Modifier,
) {
    AnimeDetailsContent(
        viewModel = viewModel,
        modifier = modifier
    )
}
