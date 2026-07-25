/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsUiState
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AnimeDetailsContent(
    viewModel: AnimeDetailsViewModel,
    modifier: Modifier = Modifier,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val state = state) {
        AnimeDetailsUiState.Loading -> {
            // TODO
        }
        is AnimeDetailsUiState.Content -> {
            Column(modifier = modifier.fillMaxSize()) {
                HeaderView(
                    headerInfoUi = state.animeDetailsUi.headerInfo,
                    modifier = modifier.fillMaxWidth()
                )
                Text(
                    text = state.loadingState.toString(),
                )
                Text(
                    text = state.animeDetailsUi.toString(),
                )
            }
        }
    }
}
