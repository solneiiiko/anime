/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shum.oks.lab.entity.anime.data.impl.datasources.AnimeLocalDataSource
import shum.oks.lab.entity.anime.data.impl.mappers.toDomainModel
import shum.oks.lab.entity.anime.domain.api.models.Anime
import shum.oks.lab.entity.anime.domain.api.repositories.AnimeRepository
import javax.inject.Inject
import javax.inject.Provider

internal class AnimeRemoteRepositoryImpl @Inject constructor(
    private val remoteMediatorProvider: Provider<AnimeRemoteMediator>,
    private val localDataSource: AnimeLocalDataSource,
) : AnimeRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun observeAnimePagingData(): Flow<PagingData<Anime>> =
        Pager(
            config = PagingConfig(
                pageSize = 25, // TODO get from settings
                enablePlaceholders = false,
            ),
            remoteMediator = remoteMediatorProvider.get(),
            pagingSourceFactory = { localDataSource.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }
}
