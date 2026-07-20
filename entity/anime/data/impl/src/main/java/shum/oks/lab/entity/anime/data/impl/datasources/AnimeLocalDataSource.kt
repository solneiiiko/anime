/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.datasources

import androidx.paging.PagingSource
import shum.oks.lab.entity.anime.data.api.dao.AnimeDao
import shum.oks.lab.entity.anime.data.api.entities.AnimeCatalog
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeSummaryEntity
import shum.oks.lab.entity.anime.data.impl.repositories.PaginationInfo
import javax.inject.Inject

internal class AnimeLocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
) {

    fun pagingSource(
        catalog: AnimeCatalog,
    ): PagingSource<Int, AnimeSummaryEntity> =
        animeDao.pagingSource(catalog.key)

    suspend fun getPaginationById(animeId: Int) =
        animeDao.getPaginationById(animeId)

    suspend fun insertAllAnimeWithPagination(
        items: List<AnimeSummaryEntity>,
        paginationInfo: PaginationInfo,
        clearExisting: Boolean,
    ) {
        animeDao.insertAllAnimeWithPaginationWithTransaction(
            items,
            items.toAnimePaginationEntityList(paginationInfo),
            clearExisting
        )
    }
}

private fun List<AnimeSummaryEntity>.toAnimePaginationEntityList(
    paginationInfo: PaginationInfo,
): List<AnimePaginationEntity> {
    val startPos =
        (paginationInfo.currentPage - PAGE_OFFSET) * paginationInfo.pageSize + INITIAL_POS_INDEX
    return mapIndexed { index, entity ->
        AnimePaginationEntity(
            id = entity.id,
            prevPage = paginationInfo.prevPage,
            nextPage = paginationInfo.nextPage,
            catalog = paginationInfo.catalog,
            position = startPos + index,
        )
    }
}

private const val INITIAL_POS_INDEX = 1
private const val PAGE_OFFSET = 1

