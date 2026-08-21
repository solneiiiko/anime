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
import shum.oks.lab.anime.config.AppEnvironment
import shum.oks.lab.anime.config.AppFeatureFlags
import shum.oks.lab.anime.config.Environment
import shum.oks.lab.anime.config.FeatureFlags

@Module
internal class AppConfigModule {

    @Provides
    fun provideEnvironment(): Environment =
        AppEnvironment

    @Provides
    fun provideFeatureFlags(): FeatureFlags =
        AppFeatureFlags
}
