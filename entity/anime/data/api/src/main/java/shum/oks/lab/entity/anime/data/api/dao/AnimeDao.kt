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
import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity

@Dao
abstract class AnimeDao {

    @Query("SELECT * FROM ${AnimeEntity.TABLE_NAME}")
    abstract suspend fun pagingSource(): PagingSource<Int, AnimeEntity>

    @Query("SELECT * FROM ${AnimePaginationEntity.TABLE_NAME} WHERE ${AnimePaginationEntity.Column.ID} = :id LIMIT 1")
    abstract suspend fun getPaginationById(id: Int): AnimePaginationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllAnime(items: List<AnimeEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllPagination(keys: List<AnimePaginationEntity>)
    @Transaction
    open suspend fun insertAllAnimeWithPaginationWithTransaction(
        items: List<AnimeEntity>,
        keys: List<AnimePaginationEntity>,
    ) {
        insertAllAnime(items)
        insertAllPagination(keys)
    }

    @Query("DELETE FROM ${AnimeEntity.TABLE_NAME}")
    abstract suspend fun clearAllAnime()
    @Query("DELETE FROM ${AnimePaginationEntity.TABLE_NAME}")
    abstract suspend fun clearAllPagination()
    @Transaction
    open suspend fun clearAllAnimeWithPaginationWithTransaction() {
        clearAllPagination()
        clearAllAnime()
    }

}
