/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import shum.oks.lab.feature.settings.screens.SettingsScreen

fun EntryProviderScope<NavKey>.settingsEntryProviders() {
    entry<SettingsScreenKey> { SettingsScreen() }
}

@Serializable
data object SettingsScreenKey : NavKey
