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
    tableName = AnimeStudioEntity.TABLE_NAME,
)
data class AnimeStudioEntity(
    @PrimaryKey
    @ColumnInfo(name = Column.ID) val id: Int,
    @ColumnInfo(name = Column.TYPE) val type: String,
    @ColumnInfo(name = Column.NAME) val name: String,
    @ColumnInfo(name = Column.URL) val url: String,
) {

    companion object {
        const val TABLE_NAME = "anime_studio"
    }

    object Column {
        const val ID = "id"
        const val TYPE = "type"
        const val NAME = "name"
        const val URL = "url"
    }
}
