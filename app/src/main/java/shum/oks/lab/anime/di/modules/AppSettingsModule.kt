/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di.modules

import dagger.Module
import dagger.Provides
import shum.oks.lab.anime.usecases.AppSettings
import shum.oks.lab.anime.usecases.GetAppSettingUseCase
import shum.oks.lab.anime.usecases.models.TopNavButton
import shum.oks.lab.entity.settings.theme.models.ContrastMode
import shum.oks.lab.entity.settings.theme.models.ThemeMode

//TODO SettingsScreen https://github.com/solneiiiko/anime/issues/27
@Module
internal class AppSettingsModule {

    @Provides
    fun provideGetAppSettingsUseCase(): GetAppSettingUseCase = object : GetAppSettingUseCase {
        override suspend fun invoke(): AppSettings =
            AppSettings(
                themeMode = ThemeMode.SYSTEM,
                contrastMode = ContrastMode.STANDARD,
                topNavButtons = TopNavButton.entries,
            )
    }
}
