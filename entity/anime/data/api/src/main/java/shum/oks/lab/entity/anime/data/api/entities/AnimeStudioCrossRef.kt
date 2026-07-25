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
import androidx.room.ForeignKey

@Entity(
    tableName = AnimeStudioCrossRef.TABLE_NAME,
    primaryKeys = [
        AnimeStudioCrossRef.Column.ANIME_ID,
        AnimeStudioCrossRef.Column.STUDIO_ID
    ],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = [AnimeEntity.Column.ID],
            childColumns = [AnimeStudioCrossRef.Column.ANIME_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeStudioEntity::class,
            parentColumns = [AnimeStudioEntity.Column.ID],
            childColumns = [AnimeStudioCrossRef.Column.STUDIO_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class AnimeStudioCrossRef(
    @ColumnInfo(name = Column.ANIME_ID) val animeId: Int,
    @ColumnInfo(name = Column.STUDIO_ID) val studioId: Int,
) {

    companion object {
        const val TABLE_NAME = "anime_studio_cross_ref"
    }

    object Column {
        const val ANIME_ID = "animeId"
        const val STUDIO_ID = "studioId"
    }
}
