/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.mvi

import shum.oks.lab.core.mvi.UiState
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUi

internal sealed interface AnimeDetailsUiState : UiState {

    data object Loading : AnimeDetailsUiState

    data class Content(
        val loadingState: UiLoadState,
        val animeDetailsUi: AnimeDetailsUi,
    ) : AnimeDetailsUiState {
        sealed interface UiLoadState {
            data object Loading : UiLoadState
            data object Success : UiLoadState
            data class Error(val message: String) : UiLoadState
        }
    }
}
