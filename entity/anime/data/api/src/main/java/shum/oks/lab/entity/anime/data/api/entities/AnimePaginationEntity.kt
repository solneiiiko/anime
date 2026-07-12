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
import androidx.room.PrimaryKey

@Entity(
    tableName = AnimePaginationEntity.TABLE_NAME,
)
data class AnimePaginationEntity(
    @PrimaryKey
    @ColumnInfo(name = Column.ID) val id: Int,
    @ColumnInfo(name = Column.PREV_PAGE) val prevPage: Int?,
    @ColumnInfo(name = Column.NEXT_PAGE) val nextPage: Int?,
) {

    companion object {
        const val TABLE_NAME = "anime_pagination"
    }

    interface Column {
        companion object {
            const val ID = "id"
            const val PREV_PAGE = "prev_page"
            const val NEXT_PAGE = "next_page"
        }
    }
}
