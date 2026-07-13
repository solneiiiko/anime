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
import shum.oks.lab.entity.anime.data.api.entities.AnimeSummaryEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import javax.inject.Inject

internal class AnimeLocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
) {

    fun pagingSource(): PagingSource<Int, AnimeSummaryEntity> =
        animeDao.pagingSource()

    suspend fun getPaginationById(animeId: Int) =
        animeDao.getPaginationById(animeId)

    suspend fun insertAllAnimeWithPagination(
        items: List<AnimeSummaryEntity>,
        paginationInfo: List<AnimePaginationEntity>,
        clearExisting: Boolean,
    ) {
        animeDao.insertAllAnimeWithPaginationWithTransaction(
            items,
            paginationInfo,
            clearExisting
        )
    }
}
