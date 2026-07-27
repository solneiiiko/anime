/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.mvi

import shum.oks.lab.core.mvi.UiState

internal sealed interface CatalogUiState : UiState {
    data object Loading : CatalogUiState

    data class Content(
        val tabs: String,// TODO Tabs https://github.com/solneiiiko/anime/issues/43
    ) : CatalogUiState
}
