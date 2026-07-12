/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.di

import dagger.Component


@Component(
    dependencies = [
        EntityAnimeDataImplDependencies::class
    ],
    modules = [
        EntityAnimeDataImplModule::class
    ]
)
internal interface EntityAnimeDataImplComponent : EntityAnimeDataImplApi
