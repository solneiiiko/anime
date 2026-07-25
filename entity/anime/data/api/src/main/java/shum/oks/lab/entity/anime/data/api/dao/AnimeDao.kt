/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction
import shum.oks.lab.entity.anime.data.api.dbmodels.AnimeDetailsDbModel
import shum.oks.lab.entity.anime.data.api.dbmodels.AnimeSummaryDbModel
import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity

@Dao
abstract class AnimeDao {

    @RewriteQueriesToDropUnusedColumns
    @Query(
        """
        SELECT ${AnimeEntity.TABLE_NAME}.*
        FROM ${AnimeEntity.TABLE_NAME}
            INNER JOIN ${AnimePaginationEntity.TABLE_NAME}
                ON ${AnimeEntity.TABLE_NAME}.${AnimeEntity.Column.ID} = ${AnimePaginationEntity.TABLE_NAME}.${AnimePaginationEntity.Column.ANIME_ID}
            WHERE ${AnimePaginationEntity.TABLE_NAME}.${AnimePaginationEntity.Column.CATALOG} = :catalog
            ORDER BY ${AnimePaginationEntity.TABLE_NAME}.${AnimePaginationEntity.Column.POSITION} ASC
        """
        )
    abstract fun pagingSource(
        catalog: String,
    ): PagingSource<Int, AnimeSummaryDbModel>

    @Query("""
        SELECT * 
        FROM ${AnimePaginationEntity.TABLE_NAME} 
        WHERE ${AnimePaginationEntity.Column.ANIME_ID} = :animeId 
            AND ${AnimePaginationEntity.Column.CATALOG} = :catalog 
        LIMIT 1
        """)
    abstract suspend fun getPaginationById(
        animeId: Int,
        catalog: String,
    ): AnimePaginationEntity?


    @Transaction
    @Query("SELECT * FROM ${AnimeEntity.TABLE_NAME} WHERE ${AnimeEntity.Column.ID} = :animeId")
    abstract suspend fun getAnimeDetailsById(
        animeId: Int
    ): AnimeDetailsDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllAnime(items: List<AnimeEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllPagination(keys: List<AnimePaginationEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllProducers(producers: List<AnimeProducerEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllProducersCrossRef(refs: List<AnimeProducerCrossRef>)
    @Transaction
    open suspend fun insertAllAnimeWithPaginationWithTransaction(
        items: List<AnimeEntity>,
        keys: List<AnimePaginationEntity>,
        producers: List<AnimeProducerEntity>,
        producerCrossRefs: List<AnimeProducerCrossRef>,
        clearExisting: Boolean,
    ) {
        if (clearExisting) {
            clearAllAnimeWithPaginationWithTransaction()
        }
        insertAllAnime(items)
        insertAllPagination(keys)
        insertAllProducers(producers)
        insertAllProducersCrossRef(producerCrossRefs)
    }

    @Query("DELETE FROM ${AnimeEntity.TABLE_NAME}")
    abstract suspend fun clearAllAnime()
    @Query("DELETE FROM ${AnimePaginationEntity.TABLE_NAME}")
    abstract suspend fun clearAllPagination()
    @Query("DELETE FROM ${AnimeProducerEntity.TABLE_NAME}")
    abstract suspend fun clearAllProducers()
    @Query("DELETE FROM ${AnimeProducerCrossRef.TABLE_NAME}")
    abstract suspend fun clearAllProducersCrossRef()
    @Transaction
    open suspend fun clearAllAnimeWithPaginationWithTransaction() {
        clearAllProducersCrossRef()
        clearAllProducers()
        clearAllPagination()
        clearAllAnime()
    }
}
