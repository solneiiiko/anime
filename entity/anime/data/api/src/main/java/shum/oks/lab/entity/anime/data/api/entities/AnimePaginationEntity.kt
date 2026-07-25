/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import shum.oks.lab.entity.anime.data.api.converters.AnimeCatalogConverter

@Entity(
    tableName = AnimePaginationEntity.TABLE_NAME,
    primaryKeys = [
        AnimePaginationEntity.Column.ANIME_ID,
        AnimePaginationEntity.Column.CATALOG
    ]
)
@TypeConverters(AnimeCatalogConverter::class)
data class AnimePaginationEntity(
    @ColumnInfo(name = Column.ANIME_ID) val id: Int,
    @ColumnInfo(name = Column.PREV_PAGE) val prevPage: Int?,
    @ColumnInfo(name = Column.NEXT_PAGE) val nextPage: Int?,
    @ColumnInfo(name = Column.CATALOG) val catalog: AnimeCatalog,
    @ColumnInfo(name = Column.POSITION) val position: Int,
) {

    companion object {
        const val TABLE_NAME = "anime_pagination"
    }

    object Column {
        const val ANIME_ID = "id"
        const val PREV_PAGE = "prev_page"
        const val NEXT_PAGE = "next_page"
        const val CATALOG = "catalog"
        const val POSITION = "position"
    }
}
