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
    tableName = AnimeLicensorCrossRef.TABLE_NAME,
    primaryKeys = [
        AnimeLicensorCrossRef.Column.ANIME_ID,
        AnimeLicensorCrossRef.Column.LICENSOR_ID
    ],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = [AnimeEntity.Column.ID],
            childColumns = [AnimeLicensorCrossRef.Column.ANIME_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeLicensorEntity::class,
            parentColumns = [AnimeLicensorEntity.Column.ID],
            childColumns = [AnimeLicensorCrossRef.Column.LICENSOR_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class AnimeLicensorCrossRef(
    @ColumnInfo(name = Column.ANIME_ID) val animeId: Int,
    @ColumnInfo(name = Column.LICENSOR_ID) val licensorId: Int,
) {

    companion object {
        const val TABLE_NAME = "anime_licensor_cross_ref"
    }

    object Column {
        const val ANIME_ID = "animeId"
        const val LICENSOR_ID = "licensorId"
    }
}
