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

@Component(
    modules = [
        AppCommonModule::class,
    ],
    dependencies = [
        AppDependencies::class,
    ],
)
internal interface AppComponent : AppComponentInternalApi