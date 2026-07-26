/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.di

import shum.oks.lab.core.di.BaseComponentHolder

object AnimeDetailsUiComponentHolder : BaseComponentHolder<
    AnimeDetailsUiApi,
    AnimeDetailsUiDependencies
>() {

    override fun buildComponent(dependencies: AnimeDetailsUiDependencies): AnimeDetailsUiApi {
        return DaggerAnimeDetailsUiComponent.builder()
            .animeDetailsUiDependencies(dependencies)
            .build()
    }
}
