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
    tableName = AnimeSummaryEntity.TABLE_NAME,
)
data class AnimeSummaryEntity(
    @PrimaryKey
    @ColumnInfo(name = Column.ID) val id: Int,
    @ColumnInfo(name = Column.TITLE) val title: String,
) {

    companion object {
        const val TABLE_NAME = "anime_summary"
    }

    object Column {
        const val ID = "id"
        const val TITLE = "title"
    }
}
