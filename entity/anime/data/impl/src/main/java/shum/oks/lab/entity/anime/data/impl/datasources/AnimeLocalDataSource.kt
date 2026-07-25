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
import shum.oks.lab.entity.anime.data.api.dbmodels.AnimeDetailsDbModel
import shum.oks.lab.entity.anime.data.api.dbmodels.AnimeSummaryDbModel
import shum.oks.lab.entity.anime.data.api.entities.AnimeCatalog
import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.impl.mappers.RelatedEntities
import shum.oks.lab.entity.anime.data.impl.repositories.PaginationInfo
import javax.inject.Inject

internal class AnimeLocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
) {

    fun pagingSource(
        catalog: AnimeCatalog,
    ): PagingSource<Int, AnimeSummaryDbModel> =
        animeDao.pagingSource(catalog.key)

    suspend fun getPaginationById(
        animeId: Int,
        catalog: AnimeCatalog,
    ) = animeDao.getPaginationById(animeId, catalog.key)

    suspend fun insertAllAnimeWithPagination(
        items: List<AnimeEntity>,
        paginationInfo: PaginationInfo,
        relatedEntities: RelatedEntities,
        clearExisting: Boolean,
    ) {
        animeDao.insertAllAnimeWithPaginationWithTransaction(
            items = items,
            keys = items.toAnimePaginationEntityList(paginationInfo),
            producers = relatedEntities.producers,
            producerCrossRefs = relatedEntities.producerCrossRefs,
            licensors = relatedEntities.licensors,
            licensorCrossRefs = relatedEntities.licensorCrossRefs,
            studios = relatedEntities.studios,
            studioCrossRefs = relatedEntities.studioCrossRefs,
            genres = relatedEntities.genres,
            genreCrossRefs = relatedEntities.genresCrossRefs,
            themes = relatedEntities.themes,
            themeCrossRefs = relatedEntities.themesCrossRefs,
            clearExisting = clearExisting,
        )
    }

    suspend fun getAnimeDetailsById(animeId: Int): AnimeDetailsDbModel? =
        animeDao.getAnimeDetailsById(animeId)
}

private fun List<AnimeEntity>.toAnimePaginationEntityList(
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

