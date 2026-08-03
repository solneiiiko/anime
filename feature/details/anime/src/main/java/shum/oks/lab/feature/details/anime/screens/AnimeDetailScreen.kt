/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsUiEffect
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsUiIntent
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsUiState
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsViewModel
import shum.oks.lab.feature.details.anime.navigation.AnimeDetailsNavigator
import shum.oks.lab.feature.details.anime.screens.composables.AnimeDetailsContent


@Composable
internal fun AnimeDetailScreen(
    viewModel: AnimeDetailsViewModel,
    navigator: AnimeDetailsNavigator,
    modifier: Modifier = Modifier,
) {

    LaunchedEffect(viewModel, navigator) {
        viewModel.effect.collect { effect ->
            handleLaunchEffect(navigator, effect)
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    val onBackClicked = remember(viewModel) {
        { viewModel.handleIntent(AnimeDetailsUiIntent.BackClicked) }
    }

    when (val state = state) {
        AnimeDetailsUiState.Loading -> {
            // TODO https://github.com/solneiiiko/anime/issues/17
        }
        is AnimeDetailsUiState.Content -> {
             AnimeDetailsContent(
                 animeDetailsUi = state.animeDetailsUi,
                 onBackClicked = onBackClicked,
                 modifier = modifier
            )
        }
    }
}

private fun handleLaunchEffect(
    navigator: AnimeDetailsNavigator,
    effect: AnimeDetailsUiEffect
) {
    when (effect) {
        is AnimeDetailsUiEffect.NavigateBack -> {
            navigator.back()
        }
    }
}
