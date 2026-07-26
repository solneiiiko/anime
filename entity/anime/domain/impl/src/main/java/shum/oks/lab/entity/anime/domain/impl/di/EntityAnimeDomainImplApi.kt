/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.domain.impl.di

import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeListUseCase
import shum.oks.lab.core.di.BaseApi
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeDetailsUseCase

interface EntityAnimeDomainImplApi : BaseApi {

    val getAnimeListUseCase: GetAnimeListUseCase

    val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
}
