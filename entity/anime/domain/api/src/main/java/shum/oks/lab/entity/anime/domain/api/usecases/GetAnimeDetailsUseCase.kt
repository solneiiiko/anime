/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.domain.api.usecases

import kotlinx.coroutines.flow.Flow
import shum.oks.lab.domain.models.DataEvent
import shum.oks.lab.entity.anime.domain.api.models.AnimeDetails

interface GetAnimeDetailsUseCase {

    suspend operator fun invoke(animeId: Int): Flow<DataEvent<AnimeDetails>>
}
