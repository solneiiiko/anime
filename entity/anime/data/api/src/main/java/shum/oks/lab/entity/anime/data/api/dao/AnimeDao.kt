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
import androidx.room.Transaction
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeSummaryEntity

@Dao
abstract class AnimeDao {

    @Query(
        """
        SELECT ${AnimeSummaryEntity.TABLE_NAME}.*
        FROM ${AnimeSummaryEntity.TABLE_NAME}
            INNER JOIN ${AnimePaginationEntity.TABLE_NAME}
                ON ${AnimeSummaryEntity.TABLE_NAME}.${AnimeSummaryEntity.Column.ID} = ${AnimePaginationEntity.TABLE_NAME}.${AnimePaginationEntity.Column.ANIME_ID}
            WHERE ${AnimePaginationEntity.TABLE_NAME}.${AnimePaginationEntity.Column.CATALOG} = :catalog
            ORDER BY ${AnimePaginationEntity.TABLE_NAME}.${AnimePaginationEntity.Column.POSITION} ASC
        """
        )
    abstract fun pagingSource(
        catalog: String,
    ): PagingSource<Int, AnimeSummaryEntity>

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllAnime(items: List<AnimeSummaryEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllPagination(keys: List<AnimePaginationEntity>)
    @Transaction
    open suspend fun insertAllAnimeWithPaginationWithTransaction(
        items: List<AnimeSummaryEntity>,
        keys: List<AnimePaginationEntity>,
        clearExisting: Boolean,
    ) {
        if (clearExisting) {
            clearAllAnimeWithPaginationWithTransaction()
        }
        insertAllAnime(items)
        insertAllPagination(keys)
    }

    @Query("DELETE FROM ${AnimeSummaryEntity.TABLE_NAME}")
    abstract suspend fun clearAllAnime()
    @Query("DELETE FROM ${AnimePaginationEntity.TABLE_NAME}")
    abstract suspend fun clearAllPagination()
    @Transaction
    open suspend fun clearAllAnimeWithPaginationWithTransaction() {
        clearAllPagination()
        clearAllAnime()
    }
}
