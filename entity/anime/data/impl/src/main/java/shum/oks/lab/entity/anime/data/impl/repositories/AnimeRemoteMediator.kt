/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import shum.oks.lab.core.network.ApiResult
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeSummaryEntity
import shum.oks.lab.entity.anime.data.impl.datasources.AnimeLocalDataSource
import shum.oks.lab.entity.anime.data.impl.datasources.AnimeRemoteDataSource
import shum.oks.lab.entity.anime.data.impl.mappers.toEntityModelList
import shum.oks.lab.entity.anime.data.impl.models.AnimeListResponse

@OptIn(ExperimentalPagingApi::class)
internal class AnimeRemoteMediator @AssistedInject constructor(
    private val remoteDataSource: AnimeRemoteDataSource,
    private val localDataSource: AnimeLocalDataSource,
    @Assisted private val initializeAction: InitializeAction,
    @Assisted private val afterRefresh: suspend () -> Unit,
) : RemoteMediator<Int, AnimeSummaryEntity>() {

    override suspend fun initialize(): InitializeAction =
        initializeAction

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeSummaryEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> INITIAL_PAGE
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = false,)
                localDataSource.getPaginationById(animeId = lastItem.id)?.nextPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = true,
                    )
            }
        }

        val animeListResponse = remoteDataSource
            .getAnimeListResponse(page = page, limit = state.config.pageSize)
        return when (animeListResponse) {
            is ApiResult.Failure<AnimeListResponse> -> {
                MediatorResult.Error(animeListResponse.exception)
            }
            is ApiResult.Success<AnimeListResponse> -> {
                val isRefresh = loadType == LoadType.REFRESH
                updateAnimeSummary(
                    animeListResponse.data,
                    clearExisting = isRefresh
                )
                if (isRefresh) {
                    afterRefresh()
                }

                MediatorResult.Success(
                    endOfPaginationReached = !animeListResponse.data.pagination.hasNextPage
                )
            }
        }
    }

    private suspend fun updateAnimeSummary(response: AnimeListResponse, clearExisting: Boolean) {
        val items = response.list
        val hasNextPage = response.pagination.hasNextPage
        val currentPage = response.pagination.currentPage
        val prevKey = if (currentPage == INITIAL_PAGE) null else currentPage - PAGE_OFFSET
        val nextKey = if (hasNextPage) currentPage + PAGE_OFFSET else null

        val keys = items.map {
            AnimePaginationEntity(
                id = it.id,
                prevPage = prevKey,
                nextPage = nextKey,
            )
        }
        localDataSource.insertAllAnimeWithPagination(
            items.toEntityModelList(),
            keys,
            clearExisting = clearExisting,
        )
    }

    companion object {
        private const val PAGE_OFFSET = 1
        private const val INITIAL_PAGE = 1
    }

    @AssistedFactory
    internal interface Factory {
        fun create(
            initializeAction: InitializeAction,
            afterRefresh: suspend () -> Unit,
        ): AnimeRemoteMediator
    }
}
