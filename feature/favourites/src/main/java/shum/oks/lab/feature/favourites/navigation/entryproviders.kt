/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.favourites.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import shum.oks.lab.feature.favourites.screens.FavouritesScreen


fun EntryProviderScope<NavKey>.favouritesEntryProviders() {
    entry<FavouritesScreenKey> { FavouritesScreen() }
}

@Serializable
data object FavouritesScreenKey : NavKey
