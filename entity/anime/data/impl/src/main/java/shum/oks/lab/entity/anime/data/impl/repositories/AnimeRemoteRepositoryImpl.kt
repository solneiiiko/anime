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
import androidx.paging.RemoteMediator
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shum.oks.lab.entity.anime.data.impl.datasources.AnimeLocalDataSource
import shum.oks.lab.entity.anime.data.impl.datasources.AnimePreferencesDataStore
import shum.oks.lab.entity.anime.data.impl.mappers.toDomainModel
import shum.oks.lab.entity.anime.domain.api.models.Anime
import shum.oks.lab.entity.anime.domain.api.repositories.AnimeRepository
import shum.oks.lab.entity.config.domain.api.AppConfigRepository
import javax.inject.Inject

internal class AnimeRemoteRepositoryImpl @Inject constructor(
    private val animeRemoteMediatorFactory: AnimeRemoteMediator.Factory,
    private val localDataSource: AnimeLocalDataSource,
    private val appConfigRepository: AppConfigRepository,
    private val animePreferencesDataStore: AnimePreferencesDataStore,
) : AnimeRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun observeAnimePagingData(): Flow<PagingData<Anime>> {
        val appPagingConfig = appConfigRepository.getAppConfig().pagingConfig

        return Pager(
            config = PagingConfig(
                pageSize = appPagingConfig.pageSize,
            ),
            remoteMediator = animeRemoteMediatorFactory.create(
                initializeAction = if (isCacheExpired()) {
                    RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
                } else {
                    RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH
                },
                afterRefresh = {
                    animePreferencesDataStore.apply {
                        setLastRefreshTime(System.currentTimeMillis())
                        setCurrentPageSize(appPagingConfig.pageSize)
                    }
                }
            ),
            pagingSourceFactory = { localDataSource.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }
    }

    private suspend fun isCacheExpired(): Boolean {
        val appConfig = appConfigRepository.getAppConfig()
        val currentPageSize = animePreferencesDataStore.getCurrentPageSize()
        if (currentPageSize != appConfig.pagingConfig.pageSize)
            return true

        val cacheTtl = appConfig.cacheConfig.ttl.inWholeMilliseconds
        val currentTime = System.currentTimeMillis()
        val lastRefreshTime = animePreferencesDataStore.getLastRefreshTime() ?: 0
        return currentTime - lastRefreshTime > cacheTtl
    }
}
