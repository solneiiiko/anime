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
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeEntity

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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllLicensors(licensors: List<AnimeLicensorEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllLicensorsCrossRef(refs: List<AnimeLicensorCrossRef>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllStudios(studios: List<AnimeStudioEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllStudiosCrossRef(refs: List<AnimeStudioCrossRef>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllGenres(genres: List<AnimeGenreEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllGenresCrossRef(refs: List<AnimeGenreCrossRef>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllThemes(themes: List<AnimeThemeEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllThemesCrossRef(refs: List<AnimeThemeCrossRef>)
    @Transaction
    open suspend fun insertAllAnimeWithPaginationWithTransaction(
        items: List<AnimeEntity>,
        keys: List<AnimePaginationEntity>,
        producers: List<AnimeProducerEntity>,
        producerCrossRefs: List<AnimeProducerCrossRef>,
        licensors: List<AnimeLicensorEntity>,
        licensorCrossRefs: List<AnimeLicensorCrossRef>,
        studios: List<AnimeStudioEntity>,
        studioCrossRefs: List<AnimeStudioCrossRef>,
        genres: List<AnimeGenreEntity>,
        genreCrossRefs: List<AnimeGenreCrossRef>,
        themes: List<AnimeThemeEntity>,
        themeCrossRefs: List<AnimeThemeCrossRef>,
        clearExisting: Boolean,
    ) {
        if (clearExisting) {
            clearAllAnimeWithPaginationWithTransaction()
        }
        insertAllAnime(items)
        insertAllPagination(keys)
        insertAllProducers(producers)
        insertAllProducersCrossRef(producerCrossRefs)
        insertAllLicensors(licensors)
        insertAllLicensorsCrossRef(licensorCrossRefs)
        insertAllStudios(studios)
        insertAllStudiosCrossRef(studioCrossRefs)
        insertAllGenres(genres)
        insertAllGenresCrossRef(genreCrossRefs)
        insertAllThemes(themes)
        insertAllThemesCrossRef(themeCrossRefs)
    }

    @Query("DELETE FROM ${AnimeEntity.TABLE_NAME}")
    abstract suspend fun clearAllAnime()
    @Query("DELETE FROM ${AnimePaginationEntity.TABLE_NAME}")
    abstract suspend fun clearAllPagination()
    @Query("DELETE FROM ${AnimeProducerEntity.TABLE_NAME}")
    abstract suspend fun clearAllProducers()
    @Query("DELETE FROM ${AnimeProducerCrossRef.TABLE_NAME}")
    abstract suspend fun clearAllProducersCrossRef()
    @Query("DELETE FROM ${AnimeLicensorEntity.TABLE_NAME}")
    abstract suspend fun clearAllLicensors()
    @Query("DELETE FROM ${AnimeLicensorCrossRef.TABLE_NAME}")
    abstract suspend fun clearAllLicensorsCrossRef()
    @Query("DELETE FROM ${AnimeStudioEntity.TABLE_NAME}")
    abstract suspend fun clearAllStudios()
    @Query("DELETE FROM ${AnimeStudioCrossRef.TABLE_NAME}")
    abstract suspend fun clearAllStudiosCrossRef()
    @Query("DELETE FROM ${AnimeGenreEntity.TABLE_NAME}")
    abstract suspend fun clearAllGenres()
    @Query("DELETE FROM ${AnimeGenreCrossRef.TABLE_NAME}")
    abstract suspend fun clearAllGenresCrossRef()
    @Query("DELETE FROM ${AnimeThemeEntity.TABLE_NAME}")
    abstract suspend fun clearAllThemes()
    @Query("DELETE FROM ${AnimeThemeCrossRef.TABLE_NAME}")
    abstract suspend fun clearAllThemesCrossRef()
    @Transaction
    open suspend fun clearAllAnimeWithPaginationWithTransaction() {
        clearAllThemesCrossRef()
        clearAllThemes()
        clearAllGenresCrossRef()
        clearAllGenres()
        clearAllStudiosCrossRef()
        clearAllStudios()
        clearAllLicensorsCrossRef()
        clearAllLicensors()
        clearAllProducersCrossRef()
        clearAllProducers()
        clearAllPagination()
        clearAllAnime()
    }
}
