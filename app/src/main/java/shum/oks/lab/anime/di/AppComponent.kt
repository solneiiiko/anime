/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di

import dagger.Component
import shum.oks.lab.anime.di.modules.AppCommonModule
import shum.oks.lab.anime.di.modules.AppFeatureModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppCommonModule::class,
        AppFeatureModule::class,
    ],
    dependencies = [
        AppDependencies::class,
    ],
)
internal interface AppComponent : AppComponentInternalApi