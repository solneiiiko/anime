/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.settings.theme.repositories

import kotlinx.coroutines.flow.Flow
import shum.oks.lab.entity.settings.theme.models.ThemeContrast
import shum.oks.lab.entity.settings.theme.models.ThemeMode

//TODO SettingsScreen https://github.com/solneiiiko/anime/issues/27
interface ThemeRepository {

    val themeMode: Flow<ThemeMode>

    suspend fun setThemeMode(themeMode: ThemeMode)

    val themeContrast: Flow<ThemeContrast>

    suspend fun setContrastMode(contrast: ThemeContrast)
}
