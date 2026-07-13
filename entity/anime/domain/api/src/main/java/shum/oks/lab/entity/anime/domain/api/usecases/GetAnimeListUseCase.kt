/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.domain.api.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import shum.oks.lab.entity.anime.domain.api.models.Anime

interface GetAnimeListUseCase {

    suspend operator fun invoke(): Flow<PagingData<Anime>>
}
