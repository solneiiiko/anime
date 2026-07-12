/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.domain.impl.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import shum.oks.lab.entity.anime.domain.api.models.Anime
import shum.oks.lab.entity.anime.domain.api.repositories.AnimeRepository
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeListUseCase
import javax.inject.Inject

internal class GetAnimeListUseCaseImpl @Inject constructor(
    private val animeRepository: AnimeRepository,
) : GetAnimeListUseCase {

    override fun invoke(): Flow<PagingData<Anime>> =
        animeRepository.observeAnimePagingData()
}
