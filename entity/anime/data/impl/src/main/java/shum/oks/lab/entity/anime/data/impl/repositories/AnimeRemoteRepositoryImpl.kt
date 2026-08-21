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
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import shum.oks.lab.domain.models.DataEvent
import shum.oks.lab.domain.models.LoadState
import shum.oks.lab.entity.anime.data.api.entities.AnimeCatalog
import shum.oks.lab.entity.anime.data.impl.datasources.AnimeLocalDataSource
import shum.oks.lab.entity.anime.data.impl.datasources.AnimePreferencesDataStore
import shum.oks.lab.entity.anime.data.impl.mappers.toAnimeDetailsModel
import shum.oks.lab.entity.anime.data.impl.mappers.toAnimeModel
import shum.oks.lab.entity.anime.domain.api.models.AnimeSummary
import shum.oks.lab.entity.anime.domain.api.models.AnimeDetails
import shum.oks.lab.entity.anime.domain.api.repositories.AnimeRepository
import shum.oks.lab.entity.config.domain.api.AppConfigRepository
import javax.inject.Inject

internal class AnimeRemoteRepositoryImpl @Inject constructor(
    private val animeRemoteMediatorFactory: AnimeRemoteMediator.Factory,
    private val localDataSource: AnimeLocalDataSource,
    private val appConfigRepository: AppConfigRepository,
    private val animePreferencesDataStore: AnimePreferencesDataStore,
    private val defaultCatalog: AnimeCatalog, // TODO get from UI
) : AnimeRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun observeAnimePagingData(): Flow<PagingData<AnimeSummary>> = flow {
        val appPagingConfig = appConfigRepository.getAppConfig().pagingConfig
        emitAll(
            Pager(
                config = PagingConfig(
                    pageSize = appPagingConfig.pageSize,
                ),
                remoteMediator = animeRemoteMediatorFactory.create(
                    initializeAction = if (isCacheExpired()) {
                        RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
                    } else {
                        RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH
                    },
                    catalog = defaultCatalog,
                    afterRefresh = {
                        animePreferencesDataStore.apply {
                            setLastRefreshTime(System.currentTimeMillis())
                            setCurrentPageSize(appPagingConfig.pageSize)
                        }
                    }
                ),
                pagingSourceFactory = { localDataSource.pagingSource(catalog = defaultCatalog) }
            ).flow.map { pagingData ->
                pagingData.map { it.toAnimeModel() }
            }
        )
    }

    override fun observeAnimeDetails(animeId: Int): Flow<DataEvent<AnimeDetails>> = flow {
        localDataSource.getAnimeDetailsById(animeId)?.let { entity ->
            // TODO get full from database -> Jikan https://github.com/solneiiiko/anime/issues/17
            // get a little from database -> animeLib
            // return from anime list (summary)
            // get from Jikan -> save to database -> return
            // get from animeLib -> save database -> return

            emit(
                DataEvent(
                    loadState = LoadState.Success,
                    data = entity.toAnimeDetailsModel()
                )
            )
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
