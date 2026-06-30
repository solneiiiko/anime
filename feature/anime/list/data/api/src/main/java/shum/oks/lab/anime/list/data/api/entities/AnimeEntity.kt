/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.list.data.api.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = AnimeEntity.TABLE_NAME,
)
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = Column.ID) val id: Int,
    @ColumnInfo(name = Column.TITLE) val title: String,
) {

    companion object {
        const val TABLE_NAME = "Anime"
    }

    interface Column {
        companion object {
            const val ID = "id"
            const val TITLE = "title"
        }
    }
}