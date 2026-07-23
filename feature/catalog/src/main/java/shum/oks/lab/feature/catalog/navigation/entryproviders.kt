/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import shum.oks.lab.feature.catalog.di.CatalogUiComponentHolder
import shum.oks.lab.feature.catalog.screens.CatalogScreen

fun EntryProviderScope<NavKey>.catalogEntryProviders(
    catalogNavigator: CatalogNavigator
) {
    entry<CatalogScreenKey> {
        CatalogScreen(
            viewModel = viewModel(
                factory = CatalogUiComponentHolder.get().viewModelFactory
            ),
            catalogNavigator = catalogNavigator
        )
    }
}

@Serializable
data object CatalogScreenKey : NavKey
