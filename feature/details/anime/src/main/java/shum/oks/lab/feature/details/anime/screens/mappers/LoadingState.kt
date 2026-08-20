/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.mappers

import shum.oks.lab.domain.models.LoadState
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsUiState


internal fun LoadState.toUiModel(): AnimeDetailsUiState.Content.UiLoadState = when (this) {
    LoadState.Loading ->
        AnimeDetailsUiState.Content.UiLoadState.Loading
    LoadState.Success ->
        AnimeDetailsUiState.Content.UiLoadState.Success
    is LoadState.Error ->
        AnimeDetailsUiState.Content.UiLoadState.Error(apiException.toString())
}
