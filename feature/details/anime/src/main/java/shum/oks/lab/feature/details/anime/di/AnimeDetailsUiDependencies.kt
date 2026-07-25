/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.di

import shum.oks.lab.core.di.BaseDependencies
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeDetailsUseCase

interface AnimeDetailsUiDependencies : BaseDependencies {

    val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
}
