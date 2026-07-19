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
import androidx.room.TypeConverters
import shum.oks.lab.entity.anime.data.api.entities.converters.AnimeTypeConverter

@Entity(
    tableName = AnimeSummaryEntity.TABLE_NAME,
)
@TypeConverters(AnimeTypeConverter::class)
data class AnimeSummaryEntity(
    @PrimaryKey
    @ColumnInfo(name = Column.ID) val id: Int,
    @ColumnInfo(name = Column.TITLE) val title: String,
    @ColumnInfo(name = Column.IMAGE_URL) val imageUrl: String? = null,
    @ColumnInfo(name = Column.SCORE) val score: Double,
    @ColumnInfo(name = Column.TYPE) val type: AnimeType? = null,
    @ColumnInfo(name = Column.EPISODES) val episodes: Int? = null,
    @ColumnInfo(name = Column.MEMBERS) val members: Int? = null,
) {

    companion object {
        const val TABLE_NAME = "anime_summary"
    }

    object Column {
        const val ID = "id"
        const val TITLE = "title"
        const val IMAGE_URL = "image_url"
        const val SCORE = "score"
        const val TYPE = "type"
        const val EPISODES = "episodes"
        const val MEMBERS = "members"
    }
}
