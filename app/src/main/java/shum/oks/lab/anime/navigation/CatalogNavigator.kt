/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.navigation

import shum.oks.lab.feature.catalog.navigation.CatalogNavigator
import shum.oks.lab.feature.catalog.navigation.CatalogItemKey
import shum.oks.lab.feature.details.anime.navigation.AnimeDetailsKey

internal fun createCatalogNavigator(
    navigator: Navigator
): CatalogNavigator = object : CatalogNavigator {

    override fun openDetails(catalogItemKey: CatalogItemKey) {
        when (catalogItemKey) {
            is CatalogItemKey.AnimeKey -> {
                navigator.navigateTo(AnimeDetailsKey(catalogItemKey.animeId))
            }
        }
    }
}
