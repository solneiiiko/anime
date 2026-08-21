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
import shum.oks.lab.core.di.DependenciesProvider
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeDetailsUseCase
import shum.oks.lab.entity.anime.domain.impl.di.EntityAnimeDomainImplComponentHolder
import shum.oks.lab.feature.details.anime.di.AnimeDetailsUiDependencies

@Module
internal class AppAnimeModule {

    @Provides
    fun provideAnimeUiDependenciesProvider(
    ): DependenciesProvider<AnimeDetailsUiDependencies> = {
        object : AnimeDetailsUiDependencies {

            override val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
                get() = EntityAnimeDomainImplComponentHolder.get().getAnimeDetailsUseCase
        }
    }
}
