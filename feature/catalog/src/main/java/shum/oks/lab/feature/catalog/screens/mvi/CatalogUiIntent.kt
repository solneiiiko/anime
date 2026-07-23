/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.mvi

import shum.oks.lab.core.mvi.UiIntent
import shum.oks.lab.feature.catalog.navigation.CatalogItemKey

internal sealed interface CatalogUiIntent : UiIntent {

    data object Init : CatalogUiIntent

    data class ItemClicked(
        val catalogItemKey: CatalogItemKey,
    ) : CatalogUiIntent
}
