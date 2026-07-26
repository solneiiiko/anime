/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.domain.impl.di

import dagger.Binds
import dagger.Module
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeDetailsUseCase
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeListUseCase
import shum.oks.lab.entity.anime.domain.impl.usecases.GetAnimeDetailsUseCaseImpl
import shum.oks.lab.entity.anime.domain.impl.usecases.GetAnimeListUseCaseImpl

@Module
internal interface EntityAnimeDomainImplModule {

    @Binds
    fun bindAnimeListUseCase(
        getAnimeListUseCaseImpl: GetAnimeListUseCaseImpl,
    ): GetAnimeListUseCase

    @Binds
    fun bindAnimeDetailsUseCase(
        getAnimeDetailsUseCaseImpl: GetAnimeDetailsUseCaseImpl,
    ): GetAnimeDetailsUseCase
}
