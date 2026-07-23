/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.mvi

import shum.oks.lab.core.mvi.UiEffect
import shum.oks.lab.feature.catalog.navigation.CatalogItemKey

internal sealed interface CatalogUiEffect : UiEffect {

    data class NavigateToDetails(
        val catalogItemKey: CatalogItemKey
    ) : CatalogUiEffect
}
