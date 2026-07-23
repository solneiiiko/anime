/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.mvi

import shum.oks.lab.core.mvi.UiState

internal sealed interface AnimeDetailsUiState : UiState {

    data object Loading : AnimeDetailsUiState
}
