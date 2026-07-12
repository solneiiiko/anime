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
import androidx.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
import shum.oks.lab.core.network.ApiResult
import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.impl.datasources.AnimeLocalDataSource
import shum.oks.lab.entity.anime.data.impl.datasources.AnimeRemoteDataSource
import shum.oks.lab.entity.anime.data.impl.mappers.toEntityModelList
import shum.oks.lab.entity.anime.data.impl.models.AnimeListResponse
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class AnimeRemoteMediator @Inject constructor(
    private val remoteDataSource: AnimeRemoteDataSource,
    private val localDataSource: AnimeLocalDataSource,
) : RemoteMediator<Int, AnimeEntity>() {

    override suspend fun initialize(): InitializeAction {
        return LAUNCH_INITIAL_REFRESH // TODO get from metadata
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {
        val page = getPageByLoadType(loadType, state)
            ?: return MediatorResult.Success(endOfPaginationReached = true)

        val animeListResponse = remoteDataSource
            .getAnimeListResponse(page = page, limit = state.config.pageSize)

        return when (animeListResponse) {
            is ApiResult.Failure<AnimeListResponse> -> {
                MediatorResult.Error(animeListResponse.exception)
            }
            is ApiResult.Success<AnimeListResponse> -> {
                val response = animeListResponse.data
                val items = response.list
                val endOfPaginationReached = !response.pagination.hasNextPage

                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearAllAnimeWithPagination()
                }

                val prevKey = if (page == INITIAL_PAGE) null else page - PAGE_OFFSET
                val nextKey = if (endOfPaginationReached) null else page + PAGE_OFFSET

                val keys = items.map {
                    AnimePaginationEntity(
                        id = it.id,
                        prevPage = prevKey,
                        nextPage = nextKey,
                    )
                }
                localDataSource.insertAllAnimeWithPagination(items.toEntityModelList(), keys)

                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
        }
    }

    private suspend fun getPageByLoadType(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>,
    ): Int? = when (loadType) {
        LoadType.REFRESH -> {
            val anchorPosition = state.anchorPosition ?: return INITIAL_PAGE
            val closestItem = state.closestItemToPosition(anchorPosition) ?: return INITIAL_PAGE
            val pagination = localDataSource.getPaginationById(animeId = closestItem.id)
            pagination?.nextPage?.minus(PAGE_OFFSET) ?: INITIAL_PAGE
        }
        LoadType.PREPEND -> {
            val firstItem = state.firstItemOrNull() ?: return null
            val pagination = localDataSource.getPaginationById(animeId = firstItem.id)
            pagination?.prevPage
        }
        LoadType.APPEND -> {
            val lastItem = state.lastItemOrNull() ?: return null
            val pagination = localDataSource.getPaginationById(animeId = lastItem.id)
            pagination?.nextPage
        }
    }

    private companion object {
        const val PAGE_OFFSET = 1
        const val INITIAL_PAGE = 1
    }
}
