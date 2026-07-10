/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.usecases

import shum.oks.lab.core.theme.models.ContrastMode
import shum.oks.lab.core.theme.models.ThemeMode

// TODO SettingsScreen https://github.com/solneiiiko/anime/issues/27
interface GetAppSettingUseCase {

    suspend operator fun invoke(): AppSettings
}

// TODO SettingsScreen https://github.com/solneiiiko/anime/issues/27
data class AppSettings(
    val themeMode: ThemeMode,
    val contrastMode: ContrastMode,
)
