/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.di

import dagger.Component


@Component(
    dependencies = [
        AnimeDetailsUiDependencies::class
    ]
)
internal abstract class AnimeDetailsUiComponent : AnimeDetailsUiApi()
